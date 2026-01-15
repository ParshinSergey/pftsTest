package ua.univer.pftsTest.helper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ua.univer.pftsTest.exeptions.MyException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConverterUtil {


    private ConverterUtil() {
    }


    public static String objectToJson(Object obj){

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(df);
        String valueAsString;
        try {
            valueAsString = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new MyException("Ошибка в методе objectToJson. " + e.getMessage());
        }
        return valueAsString;
    }


    public static <T> T jsonToObject(String json, Class<T> clas) {

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        objectMapper.setDateFormat(df);
        T t;
        try {
            t = objectMapper.readValue(json, clas);
        } catch (JsonProcessingException e) {
            throw new MyException("Ошибка в методе jsonToObject. " + e.getMessage());
        }
        return t;
    }


    public static <T> String objectToXML(T object) {

        Class<?> objectClass = object.getClass();
        Writer writer = new StringWriter();
        try {
            JAXBContext jc = JAXBContext.newInstance(objectClass);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(object, writer);
        }
        catch (JAXBException ex) {
            String message = ex.getMessage();
            if (message == null) {
                message = ex.getCause().getMessage();
                if (message == null) {
                    message = "Unidentified JAXB error";
                }
            }
            throw new MyException(message);
        }

        return writer.toString();
    }


    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(String xmlStr, Class<T> clas){

        xmlStr = xmlStr.trim().replaceFirst("^(\\W+)<","<");
        T obj;
        try {
            JAXBContext context = JAXBContext.newInstance(clas);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlStr);
            obj = (T) unmarshaller.unmarshal(reader);
        }
        catch (JAXBException e) {
            throw new MyException("Error unmarshalling. FBP Response is: " + xmlStr);
        }

        return obj;
    }
}
