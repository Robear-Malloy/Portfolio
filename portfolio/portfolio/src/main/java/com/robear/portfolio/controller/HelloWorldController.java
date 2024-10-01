package com.robear.portfolio.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/api")
    public String index() {

        return  "\nHey wait a second you're not supposed to be back here! \n" +
                "Just kidding, welcome to the API, feel free to use *most* of the GET methods. \n" +
                "If you want to make your mark please use the visitor endpoint and send a post!\n";

    }

}