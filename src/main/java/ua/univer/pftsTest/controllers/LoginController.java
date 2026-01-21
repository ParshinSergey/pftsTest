package ua.univer.pftsTest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.config.ConfigProperties;
import ua.univer.pftsTest.dto.Logon;
import ua.univer.pftsTest.exeptions.PftsException;
import ua.univer.pftsTest.helper.ConverterUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api")
public class LoginController extends BaseController{


    public LoginController(HttpClient httpClient) {
        super(httpClient);
    }


    @Scheduled(fixedRate = 365*24*60*60, initialDelay = 5, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron="0 59 8 * * MON-FRI")
    @PostMapping (value = "/v1/logon", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> logon(){

        String xmlString = "<LOGON pwd=\"111          \" lang=\"U\" />";
        ConfigProperties.USER_SID = "0";
        HttpRequest httpRequest = getHttpRequest(xmlString);

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            //throw new PftsException("Error connecting to PFTS. Message - " + e.getMessage() + "\n конец сообщения");
            logger.warn("Вход не выполнен !!!");
            return ResponseEntity.internalServerError().body("Вход не выполнен !!!");
        }

        String response = httpResponse.body();
        if (httpResponse.statusCode() != 200) {
            logger.warn(response);
            return ResponseEntity.badRequest().body(response);
        }

        logger.info(response);
        if (response.contains("LOGON")){
            Logon obj = ConverterUtil.xmlToObject(response, Logon.class);
            ConfigProperties.USER_SID = String.valueOf(obj.getSid());
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body(response);
    }



    @Scheduled(cron="15 0 22 * * MON-FRI")
    @PostMapping (value = "/v1/logoff", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> logoff(){

        String xmlString = "<LOGOFF />";

        HttpRequest httpRequest = getHttpRequest(xmlString);

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
        ConfigProperties.USER_SID = "0";
        logger.info("Method Logoff {}", response);

        return ResponseEntity.ok().body(response);
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
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to PFTS");
        }

        if (httpResponse == null){
            logger.warn("Probable problems with VPN. Try to connection.");
            makeVpnConnection("rasdial \"PFTS1\" \"univercap2\" \"dBSY73nDJumer4\"");
        }
        else {
            logger.info("{} quoterHourCheck SID - {}", httpResponse.body(), ConfigProperties.USER_SID);
            if (httpResponse.statusCode() != 200 || httpResponse.body().contains("FATAL")) {
                logger.warn("Выполняется вход в Торговую Систему ПФТС");
                logon();
            }
        }








    }


    private static void makeVpnConnection(String command)  {
        //Runtime.getRuntime().exec("rasdial \"PFTS1\" \"univercap2\" \"dBSY73nDJumer4\"");

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            logger.warn("Ошибка при выполнении соединения VPN");
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                logger.info(line);
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }



}
