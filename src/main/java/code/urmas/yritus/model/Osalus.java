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

    public Osalus(){

    }

}
