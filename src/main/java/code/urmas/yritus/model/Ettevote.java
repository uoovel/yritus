package code.urmas.yritus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ettevote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String juriidilinenimi;

    private String registrikood;

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
}
