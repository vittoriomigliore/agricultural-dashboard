package it.vittoriomigliore.agriculturaldashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index"; // This refers to the index.html in src/main/resources/templates
    }
}
