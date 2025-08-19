package com.veiculo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/")
    public String index() {
        return "Você está no mapeamento inicial";
    }

    @GetMapping("/home")
    public String home() {
        return "Você está no mapeamento Home";
    }

    @GetMapping("/soma/{num1}/{num2}")
    public String soma(@PathVariable int num1, @PathVariable int num2) {
        return "Você está no mapeamento SOMA de " + num1 + " + " + num2 + " = " + (num1 + num2);
    }

}