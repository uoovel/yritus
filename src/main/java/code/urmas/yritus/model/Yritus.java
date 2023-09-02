package code.urmas.yritus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Yritus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nimetus;

    private String aeg;
    private String koht;
    private String lisainfo;

    public Yritus(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    public String getAeg() {
        return aeg;
    }
    public void setAeg(String aeg) {
        this.aeg = aeg;
    }

    public String getKoht() {
        return koht;
    }

    public void setKoht(String koht) {
        this.koht = koht;
    }

    public String getLisainfo() {
        return lisainfo;
    }

    public void setLisainfo(String lisainfo) {
        this.lisainfo = lisainfo;
    }
}
