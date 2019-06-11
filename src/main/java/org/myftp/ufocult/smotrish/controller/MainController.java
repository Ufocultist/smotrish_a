package org.myftp.ufocult.smotrish.controller;

import org.myftp.ufocult.smotrish.domain.Channel;
import org.myftp.ufocult.smotrish.domain.User;
import org.myftp.ufocult.smotrish.repos.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private ChannelRepo channelRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(
    @RequestParam(name = "name", required = false, defaultValue = "World") String name,
    Map<String, Object> model){
    model.put("name", name);
    return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Channel> channels = channelRepo.findAll();

        if(filter != null && !filter.isEmpty()){
            channels = channelRepo.findByName(filter);
        }else {
            channels = channelRepo.findAll();
        }

        model.addAttribute("channels", channels);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam Integer number,
            Map<String, Object> model,
            @RequestParam("file")MultipartFile file
            ) throws IOException {
        Channel channel = new Channel(name, number);
        if(file !=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile=  UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            channel.setFilename(resultFilename);
        }
        channelRepo.save(channel);

        Iterable<Channel> channels = channelRepo.findAll();
        model.put("channels", channels);

        return "main";
    }

}
