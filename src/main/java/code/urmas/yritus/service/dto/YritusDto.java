package code.urmas.yritus.service.dto;

import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

public class YritusDto {
    private Long id;
    private String nimetus;
    private String aeg;
    private String koht;

    @Length(min=0, max=1000, message="{Tekst liiga pikk!}")
    private String lisainfo;
    private Timestamp aegts;
    private int osalejaid;

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

    public int getOsalejaid() {
        return osalejaid;
    }

    public void setOsalejaid(int osalejaid) {
        this.osalejaid = osalejaid;
    }
}
