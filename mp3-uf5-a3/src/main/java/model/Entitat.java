package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entitat")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Entitat {
    public String getNom() {
        return nom;
    }

    public String getTipus() {
        return tipus;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public String getComarca() {
        return comarca;
    }

    public String getClassificacioGeneral() {
        return classificacioGeneral;
    }

    public String getClassificacioEspecifica() {
        return classificacioEspecifica;
    }

    public String getDataInscripcio() {
        return dataInscripcio;
    }

    private String nom, tipus, poblacio, comarca;
    private String classificacioGeneral, classificacioEspecifica;
    private String dataInscripcio;

    public int compareTo(Object o) {
        Entitat otra = (Entitat) o;

        return this.getNom().compareTo(otra.getNom());
    }
}