package org.myftp.ufocult.smotrish.controller;

import org.myftp.ufocult.smotrish.domain.Channel;
import org.myftp.ufocult.smotrish.repos.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ChannelRepo channelRepo;

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
            @RequestParam String name,
            @RequestParam Integer number,
            Map<String, Object> model){
        Channel channel = new Channel(name, number);
        Iterable<Channel> channels = channelRepo.findAll();
        model.put("channels", channels);

        channelRepo.save(channel);
        return "main";
    }

}
