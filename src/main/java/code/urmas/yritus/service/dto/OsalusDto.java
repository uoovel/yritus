package code.urmas.yritus.service.dto;

import code.urmas.yritus.model.*;

public class OsalusDto {

    private Long id;
    private Yritus yritus;

    private Isik isik;
    private Makseviis makseviis;
    private Eraisik eraisik;
    private Ettevote ettevote;

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

    public Yritus getYritus() {
        return yritus;
    }

    public void setYritus(Yritus yritus) {
        this.yritus = yritus;
    }

    public Isik getIsik() {
        return isik;
    }

    public void setIsik(Isik isik) {
        this.isik = isik;
    }

    public Makseviis getMakseviis() {
        return makseviis;
    }

    public void setMakseviis(Makseviis makseviis) {
        this.makseviis = makseviis;
    }

    public Eraisik getEraisik() {
        return eraisik;
    }

    public void setEraisik(Eraisik eraisik) {
        this.eraisik = eraisik;
    }

    public Ettevote getEttevote() {
        return ettevote;
    }

    public void setEttevote(Ettevote ettevote) {
        this.ettevote = ettevote;
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
}
