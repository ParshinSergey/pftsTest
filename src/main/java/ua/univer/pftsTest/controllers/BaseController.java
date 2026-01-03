package ua.univer.pftsTest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {


    @GetMapping(value = "/v2/createClient")
    public String createClientTest() {
        return null;
    }
}
