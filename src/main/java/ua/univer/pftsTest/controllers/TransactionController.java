package ua.univer.pftsTest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TransactionController extends BaseController{

    public TransactionController(HttpClient httpClient) {
        super(httpClient);
    }


    @PostMapping(value = "/v1/nedDeal", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> negDeal(){

        return null;
    }


}
