package ua.univer.pftsTest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ua.univer.pftsTest.dto.OrderBook;
import ua.univer.pftsTest.helper.ConverterUtil;
import ua.univer.pftsTest.helper.SecuritiesManager;
import ua.univer.pftsTest.tables.CloseTable;
import ua.univer.pftsTest.tables.Rows;
import ua.univer.pftsTest.tables.Securities;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api/table")
public class TableController extends BaseController{


    public TableController(HttpClient httpClient) {
        super(httpClient);
    }



    @PostMapping(value = "/v1/closeTable", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> closeTable(@RequestBody CloseTable form){

        String xmlString = ConverterUtil.objectToXML(form);
        logger.info(xmlString);
        var httpResponse = sendRequest(xmlString);
        logger.info("{} closeTable", httpResponse.body());

        return ResponseEntity.ok().body(httpResponse.body());
    }



    @Scheduled(fixedRate = 365*24*60*60, initialDelay = 25, timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron="40 59 8 * * MON-FRI")
    @PostMapping(value = "/v1/tesystime", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> teSysTime(){

        String xmlString = "<TESYSTIME/>";
        HttpRequest httpRequest = getHttpRequest(xmlString);
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to PFTS. Method TeSysTime");
        }
        if (httpResponse == null){
            return ResponseEntity.status(522).body("Server is not responding");
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


    @PostMapping(value = "/v1/ext_orderBook", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> orders(@RequestBody OrderBook form){
        String xmlString = ConverterUtil.objectToXML(form);
        logger.info("{} ext_orderBook", xmlString);
        var httpResponse = sendRequest(xmlString);
        logger.info(httpResponse.body());

        return ResponseEntity.ok().body(httpResponse.body());
    }


    @PostMapping(value = "/TEST/ext_orderBook", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> orders2(@RequestBody OrderBook form){
        String xmlString = ConverterUtil.objectToXML(form);
        logger.info("{} ext_orderBook2", xmlString);
        var httpResponse = sendRequest(xmlString);
        logger.info(httpResponse.body());

        Thread.ofVirtual().start(() -> {
            CloseTable table = new CloseTable();
            table.setRef(form.getRef());
            table.setTable("EXT_ORDERBOOK");
            closeTable(table);
        });

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


    @GetMapping(value = "/v1/countSecurities", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> count() throws IOException {
        String xmlString = "<SECURITIES/>";
        logger.info("Request SECURITIES");
        var httpResponse = sendRequest(xmlString);
        String responseStr = httpResponse.body();
        Rows rows = ConverterUtil.xmlToObject(responseStr, Rows.class);
        List<Securities> securities = rows.getSecurities();
        SecuritiesManager.countOccurrencesBySecCode(securities);
        SecuritiesManager.exportDecimalsToFile(securities, "seccodes");

        //logger.info(httpResponse.body());

        return ResponseEntity.ok().body("успешно");
    }


    @GetMapping(value = "/v1/count", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> count2() throws IOException {
        String xmlString = "<SECURITIES/>";
        logger.info("Request SEC");
       // var httpResponse = sendRequest(xmlString);
        String responseStr = "<ROWS><SECURITIES a=\"CANC\" b=\"187348\" ba=\"UAH\" bb=\"100.0000\" bc=\"2029-10-12\" be=\"R\" bf=\"UA4000187348\" bg=\"\" bh=\"80\" bk=\"3\" c=\"Міністерство фінансів України\" e=\"187348\" f=\"A\" g=\"N\" h=\"SCVC\" i=\"BNSI\" j=\"1\" k=\"0.0001\" l=\"1000.0000\" m=\"UAH\" n=\"2026-01-22\" p=\"4\" q=\"0.00\" r=\"33.6500\" t=\"2029-10-12\" u=\"62.50\" v=\"182\" w=\"2026-04-17\" x=\"1000000\"/><SECURITIES a=\"CANC\" b=\"187884\" ba=\"UAH\" bb=\"100.0000\" bc=\"2029-04-27\" be=\"R\" bf=\"UA4000187884\" bg=\"\" bh=\"456\" bk=\"3\" c=\"Міністерство фінансів України\" e=\"187884\" f=\"A\" g=\"N\" h=\"SCVC\" i=\"BNSI\" j=\"1\" k=\"0.0001\" l=\"1000.0000\" m=\"UAH\" n=\"2026-01-22\" p=\"4\" q=\"0.00\" r=\"28.8500\" t=\"2029-04-27\" u=\"62.50\" v=\"182\" w=\"2026-05-01\" x=\"3250000\"/><SECURITIES a=\"CANC\" b=\"190102\" ba=\"UAH\" bb=\"100.0000\" bc=\"2026-03-25\" be=\"R\" bf=\"UA4000190102\" bg=\"\" bh=\"156\" bk=\"3\" c=\"Міністерство фінансів України\" e=\"190102\" f=\"A\" g=\"N\" h=\"SCVC\" i=\"BNSI\" j=\"1\" k=\"0.0001\" l=\"1000.0000\" m=\"UAH\" n=\"2026-01-22\" p=\"4\" q=\"0.00\" r=\"39.2300\" t=\"2026-03-25\" u=\"59.00\" v=\"182\" w=\"2026-03-25\" x=\"2500000\"/><SECURITIES a=\"CANC\" b=\"190276\" ba=\"UAH\" bb=\"100.0000\" bc=\"2026-04-22\" be=\"R\" bf=\"UA4000190276\" bg=\"\" bh=\"156\" bk=\"3\" c=\"Міністерство фінансів України\" e=\"190276\" f=\"A\" g=\"N\" h=\"SCVC\" i=\"BNSI\" j=\"1\" k=\"0.0001\" l=\"1000.0000\" m=\"UAH\" n=\"2026-01-22\" p=\"4\" q=\"0.00\" r=\"30.1200\" t=\"2026-04-22\" u=\"58.95\" v=\"182\" w=\"2026-04-22\" x=\"2500000\"/><SECURITIES a=\"CANC\" b=\"190284\" ba=\"UAH\" bb=\"100.0000\" bc=\"2026-05-27\" be=\"R\" bf=\"UA4000190284\" bg=\"\" bh=\"156\" bk=\"3\" c=\"Міністерство фінансів України\" e=\"190284\" f=\"A\" g=\"N\" h=\"SCVC\" i=\"BNSI\" j=\"1\" k=\"0.0001\" l=\"1000.0000\" m=\"UAH\" n=\"2026-01-22\" p=\"4\" q=\"0.00\" r=\"18.7700\" t=\"2026-05-27\" u=\"58.90\" v=\"182\" w=\"2026-05-27\" x=\"1200000\"/><SECURITIES a=\"CANC\" b=\"190334\" ba=\"UAH\" bb=\"100.0000\" bc=\"2026-06-03\" be=\"R\" bf=\"UA4000190334\" bg=\"\" bh=\"156\" bk=\"3\" c=\"Міністерство фінансів України\" e=\"190334\" f=\"A\" g=\"N\" h=\"SCVC\" i=\"BNSI\" j=\"1\" k=\"0.0001\" l=\"1000.0000\" m=\"UAH\" n=\"2026-01-22\" p=\"4\" q=\"0.00\" r=\"16.5000\" t=\"2026-06-03\" u=\"58.90\" v=\"182\" w=\"2026-06-03\" x=\"1500000\"/><SECURITIES a=\"CANC\" b=\"190383\" ba=\"UAH\" bb=\"100.0000\" bc=\"2026-05-06\" be=\"R\" bf=\"UA4000190383\" bg=\"\" bh=\"156\" bk=\"3\" c=\"Міністерство фінансів України\" e=\"190383\" f=\"A\" g=\"N\" h=\"SCVC\" i=\"BNSI\" j=\"1\" k=\"0.0001\" l=\"1000.0000\" m=\"UAH\" n=\"2026-01-22\" p=\"4\" q=\"0.00\" r=\"25.8100\" t=\"2026-05-06\" u=\"59.45\" v=\"182\" w=\"2026-05-06\" x=\"500000\"/></ROWS>";
        Rows rows = ConverterUtil.xmlToObject(responseStr, Rows.class);
        List<Securities> securities = rows.getSecurities();
        SecuritiesManager.countOccurrencesBySecCode(securities);
        SecuritiesManager.exportDecimalsToFile(securities, "seccodes");


        //logger.info(httpResponse.body());

        return ResponseEntity.ok().body("успешно");
    }






}
