package code.urmas.yritus.model;

import jakarta.persistence.*;

@Entity
public class Isik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nimi;

    @ManyToOne
    @JoinColumn(name="tyyp_id")
    private Tyyp tyyp;

    public Isik(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Tyyp getTyyp() {
        return tyyp;
    }

    public void setTyyp(Tyyp tyyp) {
        this.tyyp = tyyp;
    }
}
