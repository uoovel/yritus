package code.urmas.yritus.service.dto;

import code.urmas.yritus.model.*;

public class OsalusDto {
    private Yritus yritus;

    private Isik isik;
    private Makseviis makseviis;
    private Eraisik eraisik;
    private Ettevote ettevote;

    public OsalusDto(){

    };

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
}
