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
@RequestMapping(value = "/api")
public class TableController extends BaseController{

    public TableController(HttpClient httpClient) {
        super(httpClient);
    }


    @Scheduled(fixedRate = 365*24*60*60, initialDelay = 25, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron="0 6 8 * * MON-FRI")
    @PostMapping(value = "/v1/tesystime", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> teSysTime(){

        String xmlString = "<TESYSTIME/>";

        return null;
    }

    @Scheduled(initialDelay = 70, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron="0 */1 9-22 * * MON-FRI")
    private void oneMinuteRefresh(){
        String xmlString = "<REFRESH/>";
        HttpRequest httpRequest = getHttpRequest(xmlString);
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        logger.info("1 minute refresh");
    }


    @Scheduled(initialDelay = 60, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron="0 0/30 9-22 * * MON-FRI")
    private void halfHourCheck(){
        String xmlString = "<REFRESH/>";
        HttpRequest httpRequest = getHttpRequest(xmlString);
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PftsException("Error connecting to PFTS. Message - " + e.getMessage());
        }
        logger.info("halfHourCheck {}", httpResponse.body());
        logger.info("SID - {}", ConfigProperties.USER_SID);
    }


}
