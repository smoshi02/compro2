package com.roi.formController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class FormController {

    // Endpoint to load the form page
    @GetMapping("/forms/form")
    public String showForm() {
        return "/forms/form";
    }

    // Endpoint to handle form submission
    @PostMapping("/submit")
    @ResponseBody
    public String processForm(@RequestParam Map<String, String> formData) {
        System.out.println("Form Data Received:");
        formData.forEach((key, value) -> System.out.println(key + ": " + value));

        return "Form submitted successfully! Check terminal for output.";
    }
}


