package ua.univer.pftsTest.controllers;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.pftsTest.dto.NegDeal;
import ua.univer.pftsTest.exeptions.PftsException;
import ua.univer.pftsTest.helper.ConverterUtil;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping(value = "/api/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController extends BaseController{

    public TransactionController(HttpClient httpClient) {
        super(httpClient);
    }


    @PostMapping(value = "/v1/negDeal", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> negDeal(@RequestBody @Valid NegDeal order){

        String xmlString = ConverterUtil.objectToXML(order);
        logger.info(xmlString);
        var httpResponse = sendRequest(xmlString);
        logger.info("{} negDeal ", httpResponse.body());

        return getResponseEntity(httpResponse.body());
    }




}
