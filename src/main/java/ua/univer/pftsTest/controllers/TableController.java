package ua.univer.pftsTest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.config.ConfigProperties;
import ua.univer.pftsTest.exeptions.PftsException;
import ua.univer.pftsTest.helper.ConverterUtil;
import ua.univer.pftsTest.tables.Securities;

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


    @PostMapping(value = "/v1/closeTable", produces = MediaType.APPLICATION_XML_VALUE)
    public void closeTable(@RequestBody String name){

        String xmlString  = String.format("<CLOSE_TABLE table=\"%s\" />", name);
        logger.info(xmlString);
        var httpResponse = sendRequest(xmlString);
        logger.info("{} closeTable ", httpResponse.body());
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


    @PostMapping(value = "/v1/negdeals", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> negDeals(){
        String xmlString = "<NEGDEALS/>";
        var httpResponse = sendRequest(xmlString);
        logger.info(httpResponse.body());

        return ResponseEntity.ok().body(httpResponse.body());
    }




    @PostMapping(value = "/v1/securities", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> securities(@RequestBody Securities form){
        String xmlString = ConverterUtil.objectToXML(form);
        logger.info("{} Request", xmlString);
        var httpResponse = sendRequest(xmlString);
        logger.info("{} securities ", httpResponse.body());

        return ResponseEntity.ok().body(httpResponse.body());
    }



}
