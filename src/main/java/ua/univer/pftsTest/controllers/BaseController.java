package ua.univer.pftsTest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;

@RestController
@RequestMapping(value = "/api")
public class BaseController {

    public static final String TEST_URL = "https://nts-ml-vpn-uat.pfts.ua";

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected final HttpClient httpClient;

    public BaseController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


}

