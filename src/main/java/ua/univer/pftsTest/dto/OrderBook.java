package ua.univer.pftsTest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EXT_ORDERBOOK")
public class OrderBook {

    @NotBlank
    @XmlAttribute(name = "ref")
    private String ref;

    @NotBlank
    @XmlAttribute(name = "secboard")
    private String secboard;

    @NotBlank
    @XmlAttribute(name = "seccode")
    private String seccode;

    @XmlAttribute(name = "c")
    private String buysell;

    @XmlAttribute(name = "d")
    private Double price;

    @XmlAttribute(name = "e")
    private String activationTime;

    @XmlAttribute(name = "f")
    private Integer quantity;

    @XmlAttribute(name = "g")
    private Double yield;

    @XmlAttribute(name = "h")
    private Integer myOrders;

    @XmlAttribute(name = "i")
    private Integer volume;

    @XmlAttribute(name = "j")
    private Double value;


}
