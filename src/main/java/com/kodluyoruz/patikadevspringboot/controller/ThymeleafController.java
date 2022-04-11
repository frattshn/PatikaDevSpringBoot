package com.kodluyoruz.patikadevspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    // http://localhost:8080/thymeleaf1
    @GetMapping("/thymeleaf1")
    public String getTyhemeleaf1(){
        return "thymeleaf1"; //templates klasöründeki thymeleaf1.html açılır
    }

    // http://localhost:8080/thymeleaf2
    @GetMapping("/thymeleaf2")
    public String getThymeleaf2Model(Model model){
        model.addAttribute("key_model", "From Model class");
        return "thymeleaf1";
    }

}
