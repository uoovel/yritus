package code.urmas.yritus.model;

import jakarta.persistence.*;

@Entity
public class Ettevote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String juriidilinenimi;

    private String registrikood;

    @OneToOne
    @JoinColumn(name="isik_id")
    private Isik isik;

    public Ettevote(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Isik getIsik() {
        return isik;
    }

    public void setIsik(Isik isik) {
        this.isik = isik;
    }
}
