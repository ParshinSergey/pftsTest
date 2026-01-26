package ua.univer.pftsTest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.univer.pftsTest.helper.DoubleAdapter;
import ua.univer.pftsTest.helper.DoubleAdapter4Digits;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NEGDEAL")
public class NegDeal {

    @NotBlank
    @XmlAttribute(name = "account")
    private String account;

    @NotBlank
    @Pattern(regexp = "[BS]")
    @XmlAttribute(name = "buysell")
    private String buysell;

    @NotBlank
    @XmlAttribute(name = "secboard")
    private String secboard;

    @NotBlank
    @XmlAttribute(name = "seccode")
    private String seccode;

    @NotBlank
    @XmlAttribute(name = "cpfirmid")
    private String cpfirmid;

    @XmlJavaTypeAdapter(DoubleAdapter4Digits.class)
    @XmlAttribute(name = "price")
    private double price;

    @NotNull
    @XmlAttribute(name = "quantity")
    private int quantity;

    @Size(max = 20)
    @XmlAttribute(name = "brokerref")
    private String brokerref;

    @Size(max = 10)
    @XmlAttribute(name = "matchref")
    private String matchref;

    @NotBlank
    @XmlAttribute(name = "settlecode")
    private String settlecode;

    @XmlAttribute(name = "extref")
    private String extref;

    @XmlAttribute(name = "acceptedorderno")
    private Integer acceptedorderno;

    @Size(max = 12)
    @XmlAttribute(name = "clientcode")
    private String clientcode;

    @XmlAttribute(name = "expiry")
    private String expiry;

    @XmlJavaTypeAdapter(DoubleAdapter.class)
    @XmlAttribute(name = "value")
    private Double value;


}
