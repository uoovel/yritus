package code.urmas.yritus.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Yritus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nimetus;

    private String aeg;
    private String koht;

    @Column(length = 1000)
    private String lisainfo;

    private Timestamp aegts;

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

    public Timestamp getAegts() {
        return aegts;
    }

    public void setAegts(Timestamp aegts) {
        this.aegts = aegts;
    }
}
