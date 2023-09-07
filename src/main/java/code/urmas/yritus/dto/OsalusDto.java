package code.urmas.yritus.dto;

import code.urmas.yritus.model.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class OsalusDto {

    private Long id;
    private Long yritusid;
    private String yritusenimetus;
    private String yrituseaeg;
    private String yritusekoht;
    private Long isikutyypid;
    private Long makseviisid;
    private String makseviisinimetus;
    private Long eraisikid;
    private String eesnimi;
    private String perekonnanimi;
    @Length(min=11, max=11, message="{Isikukoodi pikkus?}")
    @Pattern(regexp = "(1|2|3|4)[0-9]{10}", message = "{Isikukoodi muster?}")
    private String isikukood;
    private Long ettevoteid;

    private String juriidilinenimi;
    private String registrikood;
    private Integer tulijatearv;
    private String lisainfo;

    public OsalusDto(){

    };
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTulijatearv() {
        return tulijatearv;
    }

    public void setTulijatearv(Integer tulijatearv) {
        this.tulijatearv = tulijatearv;
    }

    public String getLisainfo() {
        return lisainfo;
    }

    public void setLisainfo(String lisainfo) {
        this.lisainfo = lisainfo;
    }

    public String getIsikukood() {
        return isikukood;
    }

    public void setIsikukood(String isikukood) {
        this.isikukood = isikukood;
    }

    public Long getIsikutyypid() {
        return isikutyypid;
    }

    public void setIsikutyypid(Long isikutyypid) {
        this.isikutyypid = isikutyypid;
    }

    public String getEesnimi() {
        return eesnimi;
    }

    public void setEesnimi(String eesnimi) {
        this.eesnimi = eesnimi;
    }

    public String getPerekonnanimi() {
        return perekonnanimi;
    }

    public void setPerekonnanimi(String perekonnanimi) {
        this.perekonnanimi = perekonnanimi;
    }

    public Long getYritusid() {
        return yritusid;
    }

    public void setYritusid(Long yritusid) {
        this.yritusid = yritusid;
    }

    public Long getMakseviisid() {
        return makseviisid;
    }

    public void setMakseviisid(Long makseviisid) {
        this.makseviisid = makseviisid;
    }

    public String getYritusenimetus() {
        return yritusenimetus;
    }

    public void setYritusenimetus(String yritusenimetus) {
        this.yritusenimetus = yritusenimetus;
    }

    public String getYrituseaeg() {
        return yrituseaeg;
    }

    public void setYrituseaeg(String yrituseaeg) {
        this.yrituseaeg = yrituseaeg;
    }

    public String getYritusekoht() {
        return yritusekoht;
    }

    public void setYritusekoht(String yritusekoht) {
        this.yritusekoht = yritusekoht;
    }

    public Long getEraisikid() {
        return eraisikid;
    }

    public void setEraisikid(Long eraisikid) {
        this.eraisikid = eraisikid;
    }

    public String getMakseviisinimetus() {
        return makseviisinimetus;
    }

    public void setMakseviisinimetus(String makseviisinimetus) {
        this.makseviisinimetus = makseviisinimetus;
    }

    public Long getEttevoteid() {
        return ettevoteid;
    }

    public void setEttevoteid(Long ettevoteid) {
        this.ettevoteid = ettevoteid;
    }

    public String getJuriidilinenimi() {
        return juriidilinenimi;
    }

    public void setJuriidilinenimi(String juriidilinenimi) {
        this.juriidilinenimi = juriidilinenimi;
    }

    public String getRegistrikood() {
        return registrikood;
    }

    public void setRegistrikood(String registrikood) {
        this.registrikood = registrikood;
    }
}
