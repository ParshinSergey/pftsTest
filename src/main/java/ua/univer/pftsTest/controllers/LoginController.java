package ua.univer.pftsTest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.exeptions.PftsException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@RestController
@RequestMapping(value = "/api")
public class LoginController extends BaseController{


    public LoginController(HttpClient httpClient) {
        super(httpClient);
    }


    @PostMapping (value = "/v1/logon", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> logon(){


        String xmlString = "<LOGON pwd=\"111          \" lang=\"U\" />";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(TEST_URL))
                .timeout(Duration.ofSeconds(30))
                .POST(HttpRequest.BodyPublishers.ofString(xmlString))
                .header("Content-Type", "application/xml")
                .header("SID", "0")
                .header("UID", "UNIKA00009")
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PftsException("Error connecting to Dekra-service. Message - " + e.getMessage());
        }
        if (httpResponse.statusCode() == 500) {
            if(httpResponse.body() != null){
                logger.warn(httpResponse.body());
            }
            throw new PftsException("Status 500 at Dekra-service Response");
        }


        return ResponseEntity.ok().body(httpResponse.body());
    }



}
