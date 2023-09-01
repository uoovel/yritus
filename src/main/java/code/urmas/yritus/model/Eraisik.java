package code.urmas.yritus.model;

import jakarta.persistence.*;

@Entity
public class Eraisik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Eesnimi;
    private String Perekonnanimi;
    private String Isikukood;

    @OneToOne
    @JoinColumn(name="isik_id")
    private Isik isik;

    public Eraisik(){

    }

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

    public Isik getIsik() {
        return isik;
    }

    public void setIsik(Isik isik) {
        this.isik = isik;
    }
}
