package ua.univer.pftsTest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;

public class BaseController {

    public static final String TEST_URL = "https://nts-ml-vpn-uat.pfts.ua";

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected final HttpClient httpClient;

    public BaseController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


}

