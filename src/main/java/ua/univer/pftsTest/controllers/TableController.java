package ua.univer.pftsTest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.config.ConfigProperties;
import ua.univer.pftsTest.exeptions.PftsException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api/table")
public class TableController extends BaseController{


    public TableController(HttpClient httpClient) {
        super(httpClient);
    }


    @Scheduled(fixedRate = 365*24*60*60, initialDelay = 25, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron="40 59 8 * * MON-FRI")
    @PostMapping(value = "/v1/tesystime", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> teSysTime(){

        String xmlString = "<TESYSTIME/>";
        HttpRequest httpRequest = getHttpRequest(xmlString);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PftsException("Error connecting to PFTS. Message - " + e.getMessage());
        }
        logger.info("{} teSysTime ", httpResponse.body());

        return ResponseEntity.ok().body(httpResponse.body());
    }


    @Scheduled(cron="*/30 * 9-21 * * MON-FRI")
    private void refresh(){
        String xmlString = "<REFRESH/>";
        HttpRequest httpRequest = getHttpRequest(xmlString);
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
    }


    @Scheduled(cron="0 0/15 9-21 * * MON-FRI")
    private void quoterHourCheck(){
        String xmlString = "<REFRESH/>";
        HttpRequest httpRequest = getHttpRequest(xmlString);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PftsException("Error connecting to PFTS. Message - " + e.getMessage() + "\n конец сообщения");
        }
        logger.info("{} quoterHourCheck SID - {}", httpResponse.body(), ConfigProperties.USER_SID);
    }


    @PostMapping(value = "/v1/negdeals", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> negDeals(){
        String xmlString = "<NEGDEALS/>";
        HttpRequest httpRequest = getHttpRequest(xmlString);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PftsException("Error connecting to PFTS. Message - " + e.getMessage() + "\n конец сообщения");
        }
        logger.info(httpResponse.body());

        return ResponseEntity.ok().body(httpResponse.body());
    }


}
