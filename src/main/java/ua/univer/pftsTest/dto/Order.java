package ua.univer.pftsTest.dto;

import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.univer.pftsTest.helper.DoubleAdapter4Digits;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ORDER")
public class Order {

    @NotBlank
    @XmlAttribute(name = "account")
    private String account;

    @NotBlank
    @Pattern(regexp = "[BS]")
    @XmlAttribute(name = "buysell")
    private String buysell;

    @NotBlank
    @Pattern(regexp = "[ML]")
    @XmlAttribute(name = "mktlimit")
    private String mktlimit;

    @NotBlank
    @Pattern(regexp = "[SO]")
    @XmlAttribute(name = "splitflag")
    private String splitflag;

    @NotEmpty
    @Pattern(regexp = "[ NW]")
    @XmlAttribute(name = "immcancel")
    private String immcancel;

    @NotBlank
    @XmlAttribute(name = "secboard")
    private String secboard;

    @NotBlank
    @XmlAttribute(name = "seccode")
    private String seccode;

    @XmlJavaTypeAdapter(DoubleAdapter4Digits.class)
    @XmlAttribute(name = "price")
    private Double price;

    @NotNull
    @XmlAttribute(name = "quantity")
    private int quantity;

    @Size(max = 20)
    @XmlAttribute(name = "brokerref")
    private String brokerref;

    @XmlAttribute(name = "extref")
    private String extref;

    @Size(max = 12)
    @XmlAttribute(name = "clientcode")
    private String clientcode;


}
