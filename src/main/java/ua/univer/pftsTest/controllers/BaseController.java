package ua.univer.pftsTest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.config.ConfigProperties;
import ua.univer.pftsTest.exeptions.PftsException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@RestController
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


    HttpResponse<String> sendRequest(String xmlString) {
        HttpRequest httpRequest = getHttpRequest(xmlString);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PftsException("Error connecting to PFTS. Message - " + e.getMessage());
        }
        return httpResponse;
    }


    ResponseEntity<String> getResponseEntity(String response){
        if (response.contains("FATAL") || response.contains("BAD") ){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


}

