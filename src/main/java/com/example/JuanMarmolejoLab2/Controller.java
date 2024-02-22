package com.example.JuanMarmolejoLab2;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String home(Model model){
        LocalTime now = LocalTime.now();

        String greeting = "";

        if(now.isBefore(LocalTime.of(12,0))){
            greeting = "Good morning, Juan Marmolejo, Welcome to COMP367";
        } else{
            greeting = "Good afternoon, Juan Marmolejo, Welcome to COMP367";
        }

        model.addAttribute("greeting", greeting);
        return "index";
    }
}
