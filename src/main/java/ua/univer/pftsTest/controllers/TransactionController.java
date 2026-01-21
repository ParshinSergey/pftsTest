package ua.univer.pftsTest.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pfts.midlay.Utils;
import ua.pfts.midlay.utils.ExecutionException;
import ua.univer.pftsTest.config.ConfigProperties;
import ua.univer.pftsTest.dto.NegDeal;
import ua.univer.pftsTest.helper.ConverterUtil;

import java.net.http.HttpClient;

@RestController
@RequestMapping(value = "/api/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController extends BaseController{

    @Autowired
    private Utils utils;

    public TransactionController(HttpClient httpClient) {
        super(httpClient);
    }


    @PostMapping(value = "/TEST/negDeal", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> negDeal(@RequestBody @Valid NegDeal order){

        String xmlString = ConverterUtil.objectToXML(order);
        logger.info(xmlString);
        var httpResponse = sendRequest(xmlString);
        logger.info("{} negDeal ", httpResponse.body());

        return getResponseEntity(httpResponse.body());
    }


    @PostMapping(value = "/v1/sign", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> negDeal(@RequestBody String words) throws ExecutionException {

        logger.info(words);

        final String result = utils.signatureData(ConfigProperties.KEY_PATH, words);
        logger.info(result);

        return getResponseEntity(result);
    }




}
