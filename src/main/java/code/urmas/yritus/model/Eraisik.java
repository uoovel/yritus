package code.urmas.yritus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Eraisik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Eesnimi;
    private String Perekonnanimi;
    private String Isikukood;

    public Eraisik(){

    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEesnimi() {
        return Eesnimi;
    }

    public void setEesnimi(String eesnimi) {
        Eesnimi = eesnimi;
    }

    public String getPerekonnanimi() {
        return Perekonnanimi;
    }

    public void setPerekonnanimi(String perekonnanimi) {
        Perekonnanimi = perekonnanimi;
    }

    public String getIsikukood() {
        return Isikukood;
    }

    public void setIsikukood(String isikukood) {
        Isikukood = isikukood;
    }
}
