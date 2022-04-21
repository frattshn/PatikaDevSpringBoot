package com.kodluyoruz.patikadevspringboot.tutorials.controller;

import com.kodluyoruz.patikadevspringboot.tutorials.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {

    //option
    // http://localhost:8080
    @GetMapping({"/", "index"})
    public String index() {
        return "index";
    }

    // http://localhost:8080/thymeleaf1
    @GetMapping("/thymeleaf1")
    public String getTyhemeleaf1() {
        return "thymeleaf1"; //templates klasöründeki thymeleaf1.html açılır
    }

    // http://localhost:8080/thymeleaf2
    @GetMapping("/thymeleaf2")
    public String getThymeleaf2Model(Model model) {
        model.addAttribute("key_model", "From Model class");
        return "thymeleaf1";
    }

    // http://localhost:8080/thymeleaf3
    @GetMapping("/thymeleaf3")
    public String getThymeleaf3Model(Model model) {
        model.addAttribute("key_model", "From Model class");
        return "thymeleaf2";
    }

    // Model Object gönderme
    // http://localhost:8080/thymeleaf4
    @GetMapping("/thymeleaf4")
    public String getThymeleaf4Model(Model model) {
        model.addAttribute("key_model1", "text");
        ProductDto productDto = ProductDto
                .builder()
                .productID(0L)
                .productName("Ürün adı")
                .productPrice(2500)
                .build();
        model.addAttribute("key_model2", productDto);
        return "thymeleaf3";
    }

    // Model Object List gönderme
    // http://localhost:8080/thymeleaf5
    @GetMapping("/thymeleaf5")
    public String getThymeleaf5Model(Model model) {
        model.addAttribute("key_model1", "text");
        List<ProductDto> list = new ArrayList<>();
        list.add(ProductDto.builder().productID(0L).productName("Ürün adı").productPrice(2500).build());
        list.add(ProductDto.builder().productID(1L).productName("Ürün adı 2").productPrice(25).build());
        list.add(ProductDto.builder().productID(2L).productName("Ürün adı 3").productPrice(5800).build());
        model.addAttribute("productList", list);
        return "thymeleaf4";
    }

    // http://localhost:8080/thymeleaf6/
    @GetMapping({"/thymeleaf6/{id}", "/thymeleaf6"})
    public String getThymeleaf6Model(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("key_model1", "id : " + id);
        } else {
            model.addAttribute("key_model1", "id bulunamadı");
        }
        return "thymeleaf5";
    }

    //@RequestParam
    // http://localhost:8080/thymeleaf6?id=4
    @GetMapping("/thymeleaf7")
    public String getThymeleaf7Model(Model model, @RequestParam(name = "id", defaultValue = "0", required = false) Long id) {
        model.addAttribute("key_model1", "id : " + id);
        return "thymeleaf6";
    }


}
