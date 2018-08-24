package com.dognessnetwork.customer.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketConfigController {

    @Value("${customer.sockjs}")
    private String sockjs;

    @Value("${customer.wesocket}")
    private String wesocket;

    @RequestMapping("/sockjs")
    public String sockjs() {
        return sockjs;
    }

    @RequestMapping("/wesocket")
    public String wesocket() {
        return wesocket;
    }
}
