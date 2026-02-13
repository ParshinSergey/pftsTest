package ua.univer.pftsTest.dto;

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
@XmlRootElement(name = "OK")
public class Ok {

    @XmlAttribute(name = "code")
    private String code;

    @XmlAttribute(name = "msg")
    private String msg;

    @XmlAttribute(name = "orderNo")
    private String orderNo;

    public void setOrder(String msg){
        this.orderNo = msg.replaceAll("\\D", "");
    }

}
