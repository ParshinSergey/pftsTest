package ua.univer.pftsTest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.config.ConfigProperties;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

@RestController
@RequestMapping(value = "/api")
public class BaseController {

    public static final String TEST_URL = "https://nts-ml-vpn-uat.pfts.ua";

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected final HttpClient httpClient;


    public BaseController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }




    static HttpRequest getHttpRequest(String xmlString) {
        return HttpRequest.newBuilder()
                .uri(URI.create(ConfigProperties.SERVER_URL))
                .timeout(Duration.ofSeconds(30))
                .POST(HttpRequest.BodyPublishers.ofString(xmlString))
                .header("Content-Type", "application/xml")
                .header("SID", ConfigProperties.USER_SID)
                .header("UID", ConfigProperties.USER_UID)
                .build();
    }


}

