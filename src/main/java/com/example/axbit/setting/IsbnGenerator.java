package com.example.axbit.setting;

public class IsbnGenerator {
    public static String isbnGenerator() {
        int codeBody1 = (int) Math.round(Math.random() * 100);
        String registrantNumber = String.valueOf(codeBody1);
        if (registrantNumber.length() < 2) {
            registrantNumber = "0" + codeBody1;
        }
        int codeBody2 = (int) Math.round(Math.random() * 1000000);
        String editionNumber = String.valueOf(codeBody2);
        if (editionNumber.length() < 6) {
            editionNumber = "0" + codeBody2;
        }
        int control;
        int sum = 0;

        String codeBody = "9785" + registrantNumber + editionNumber;

        for (int i = 0; i < 12; i++) {
            int presentValue = Integer.parseInt(String.valueOf(codeBody.charAt(i)));
            if (i % 2 == 0) {
                sum = sum + presentValue;
            } else {
                sum = sum + presentValue * 3;
            }
        }
        control = 10 - sum % 10;
        if (control == 10) {
            control = 0;
        }
        return "978-5-" + registrantNumber + "-" + editionNumber + "-" + control;
    }
}
