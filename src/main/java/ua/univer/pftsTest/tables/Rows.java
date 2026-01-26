package ua.univer.pftsTest.tables;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ROWS", namespace = "")
public class Rows {

    @XmlElement(name="SECURITIES", type=Securities.class)
    public List<Securities> securities = new ArrayList<>();


}
