package com.ilzirabalobanova.inovus.numbergenerator.controller;

import com.ilzirabalobanova.inovus.numbergenerator.service.INumberGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class NumberGeneratorController {
    private final INumberGeneratorService generatorService;

    @Autowired
    public NumberGeneratorController(INumberGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping("/random")
    public String random() {
        String number = generatorService.generateRandomNumber();
        return "random-number-view";
    }

    @GetMapping("/next")
    public String next() {
        String number = generatorService.generateNextNumber();
        return "random-number-view";
    }

}
