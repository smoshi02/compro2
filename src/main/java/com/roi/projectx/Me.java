package com.roi.projectx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class Me {
    public static void main(String[] args) {
        SpringApplication.run(Me.class, args);
    }
    @GetMapping("about-me")
    public String AboutMe(Model model) {
        String fullName = "Roineill Genove";
        String favoriteQuote = "The most effective way to do it, is to do it";
        String descriptionAboutSelf = "A Computer Science Student from Lorma Colleges and i love to eat.";

        model.addAttribute("fullName", fullName);
        model.addAttribute("favoriteQuote", favoriteQuote);
        model.addAttribute("descriptionAboutSelf", descriptionAboutSelf);

        return "about_me";
    }
}
