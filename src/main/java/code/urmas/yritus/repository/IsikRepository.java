package code.urmas.yritus.repository;

import code.urmas.yritus.model.Isik;
import code.urmas.yritus.model.Tyyp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IsikRepository extends JpaRepository<Isik, Long> {


    List<Isik> findByTyyp(Tyyp tyyp);
}
