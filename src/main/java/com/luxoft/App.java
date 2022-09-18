package com.luxoft;

import com.luxoft.service.ValidationServiceImpl;

public class App {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Enter 1 file name!");
        }
        new ValidationServiceImpl(args[0]);
    }
}
