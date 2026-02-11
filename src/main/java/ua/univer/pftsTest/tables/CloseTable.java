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
@XmlRootElement(name = "CLOSE_TABLE")
public class CloseTable {

    @NotBlank
    @XmlAttribute(name = "table")
    private String table;

    @XmlAttribute(name = "ref")
    private String ref;

}

