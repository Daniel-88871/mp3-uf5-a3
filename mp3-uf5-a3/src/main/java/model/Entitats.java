package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entitats")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Entitats {

    @XmlElement(name = "entitat")
    private List<Entitat> llistaEntitats;

    public List<Entitat> getLlistaEntitats() {
        return llistaEntitats;
    }
}