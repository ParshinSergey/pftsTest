package ua.univer.pftsTest.helper;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Locale;

// над полем в сериализуемом классе ставим аннотацию
// @XmlJavaTypeAdapter(DoubleAdapter.class)
// protected Double value;

public class DoubleAdapter extends XmlAdapter<String, Double> {
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
        //Edit the format to your needs
        return String.format(Locale.ROOT,"%.2f", v);
    }
}
