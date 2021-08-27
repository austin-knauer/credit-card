package com.austinknauer.creditcard.controllers;

import com.austinknauer.creditcard.dtos.Number;
import com.austinknauer.creditcard.services.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveCard(@RequestBody Number number, HttpServletResponse res) {
        boolean valid = numberService.validate(number.getNumber());

        if (valid) {
            res.setStatus(200);
            return "Card number is valid.";
        } else {
            res.setStatus(400);
            return "Card number is invalid.";
        }
    }
}
