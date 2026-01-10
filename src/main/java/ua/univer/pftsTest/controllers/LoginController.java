package ua.univer.pftsTest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.config.ConfigProperties;
import ua.univer.pftsTest.dto.Logon;
import ua.univer.pftsTest.exeptions.PftsException;
import ua.univer.pftsTest.helper.ConverterUtil;

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
            throw new PftsException("Error connecting to PFTS. Message - " + e.getMessage());
        }


        String response = httpResponse.body();
        if (httpResponse.statusCode() != 200) {
            logger.warn(response);
            return ResponseEntity.badRequest().body(response);
        }

        logger.info(response);
        if (response.matches("LOGON")){
            Logon obj = ConverterUtil.xmlToObject(response, Logon.class);
            ConfigProperties.USER_SID = String.valueOf(obj.getSid());

            System.out.println("SID - " + ConfigProperties.USER_SID);
            
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body(response);
    }



}
