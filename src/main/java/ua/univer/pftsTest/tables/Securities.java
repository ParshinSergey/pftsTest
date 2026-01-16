package ua.univer.pftsTest.tables;

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
@XmlRootElement(name = "SECURITIES")
public class Securities {

    @NotBlank
    @XmlAttribute(name = "a")
    private String secboard;;

    @NotBlank
    @XmlAttribute(name = "b")
    private String seccode;

    @XmlAttribute(name = "f")
    private String status;


}
