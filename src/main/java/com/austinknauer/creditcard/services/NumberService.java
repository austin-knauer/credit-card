package com.austinknauer.creditcard.services;

import org.springframework.stereotype.Service;

@Service
public class NumberService {

    public boolean validate(String number) {

        if (number.length() < 14 || number.length() > 19 || number.matches("\\D")) {
            return false;
        }

        int lastDigit = Character.getNumericValue(number.charAt(number.length() - 1));
        int finalSum = 0;

        for (int i = number.length() - 2; i >= 0; i--) {
            if (i % 2 == 0) {
                int newNum = Character.getNumericValue(number.charAt(i)) * 2;
                if (newNum > 9) {
                    newNum = (newNum / 10) + (newNum % 10);
                }
                System.out.println(newNum);
                finalSum += newNum;
            } else {
                finalSum += Character.getNumericValue(number.charAt(i));
            }
        }


        int fromTen = 10 - (finalSum % 10);
        return fromTen == lastDigit;
    }
}
