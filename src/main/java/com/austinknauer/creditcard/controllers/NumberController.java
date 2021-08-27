package com.austinknauer.creditcard.controllers;

import com.austinknauer.creditcard.services.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/card")
public class NumberController {

    private NumberService numberService;

    @Autowired
    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping("/save")
    public String saveCard(@RequestBody String number, HttpServletResponse res) {
        boolean valid = numberService.validate(number);

        if (valid) {
            res.setStatus(200);
            return "Card number is valid.";
        } else {
            res.setStatus(400);
            return "Card number is invalid.";
        }
    }
}
