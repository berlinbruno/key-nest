package dev.berlinbruno.key_nest.controller;

import dev.berlinbruno.key_nest.model.Secret;
import dev.berlinbruno.key_nest.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final SecretService secretService;

    @Autowired
    public HomeController(SecretService secretService) {
        this.secretService = secretService;
    }

    private String getUserId(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            return oauth2User.getAttribute("sub");
        }
        return null;
    }

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        String userId = getUserId(authentication);
        if (userId != null) {
            String name = ((OAuth2User) authentication.getPrincipal()).getAttribute("name");
            String picture = ((OAuth2User) authentication.getPrincipal()).getAttribute("picture");

            model.addAttribute("name", name);
            model.addAttribute("picture", picture);

            List<Secret> secrets = secretService.findAllSecretsByUserId(userId);
            model.addAttribute("totalSecrets", secrets.size());
            model.addAttribute("secrets", secrets);
        }

        return "index";
    }

    @PostMapping("/addSecret")
    public String addSecret(@RequestParam String secretName,
                            @RequestParam String name,
                            @RequestParam String value,
                            @RequestParam String category,
                            @RequestParam(required = false) String notes,
                            Authentication authentication) {
        String userId = getUserId(authentication);
        if (userId != null) {
            Secret secret = new Secret();
            secret.setSecretName(secretName);
            secret.setName(name);
            secret.setValue(value);
            secret.setCategory(category);
            secret.setNotes(notes);
            secret.setUserId(userId);
            secretService.saveSecret(secret);
        }

        return "redirect:/";
    }

    @PostMapping("/updateSecret")
    public String updateSecret(@RequestParam String secretName,
                               @RequestParam String id,
                               @RequestParam String name,
                               @RequestParam String value,
                               @RequestParam String category,
                               @RequestParam(required = false) String notes) {
        Secret secret = new Secret();
        secret.setSecretName(secretName);
        secret.setName(name);
        secret.setValue(value);
        secret.setCategory(category);
        secret.setNotes(notes);
        secretService.updateSecret(secret, id);

        return "redirect:/";
    }

    @PostMapping("/deleteSecret/{id}")
    public String deleteSecret(@PathVariable String id) {
        secretService.deleteSecret(id);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
