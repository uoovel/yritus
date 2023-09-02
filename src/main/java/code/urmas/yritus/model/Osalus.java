package code.urmas.yritus.model;

import jakarta.persistence.*;

@Entity
public class Osalus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="yritus_id")
    private Yritus yritus;

    @ManyToOne
    @JoinColumn(name="isik_id")
    private Isik isik;

    @ManyToOne
    @JoinColumn(name="makseviis_id")
    private Makseviis makseviis;

    private Integer tulijatearv;
    private String lisainfo;

    public Osalus(){

    }

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
