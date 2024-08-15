package dev.berlinbruno.key_nest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        // Add the authenticated user's details to the model
        model.addAttribute("name", authentication.getName());
        model.addAttribute("email", authentication.getPrincipal().toString());

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // This will map to the login.html template
    }

}
