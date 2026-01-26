package ua.univer.pftsTest.helper;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Locale;

public class DoubleAdapter4Digits extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty() || v.equals("null")) {
            return null;
        }
        return Double.parseDouble(v);
    }

    @Override
    public String marshal(Double v) {
        if (v == null) {
            return null;
        }
        return String.format(Locale.ROOT,"%.4f", v);
    }
}
