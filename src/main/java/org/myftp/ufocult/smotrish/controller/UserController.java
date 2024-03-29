package org.myftp.ufocult.smotrish.controller;

import org.myftp.ufocult.smotrish.domain.Role;
import org.myftp.ufocult.smotrish.domain.User;
import org.myftp.ufocult.smotrish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam Map<String, String> form,
            @RequestParam ("userId") User user
    ){
        userService.saveUser(user, username, firstname, lastname, form);

        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/delete/{user.id}", method = RequestMethod.GET)
    public String userDel(
            @PathVariable ("user.id") Long id
    ){
        userService.deleteUser(id);

        return "userList";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ){
        userService.updateProfile(user, password, email);
        return "redirect:/user/profile";
    }
}
