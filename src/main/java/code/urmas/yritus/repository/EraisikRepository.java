package code.urmas.yritus.repository;

import code.urmas.yritus.model.Eraisik;
import code.urmas.yritus.model.Isik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EraisikRepository extends JpaRepository<Eraisik, Long> {

    Eraisik findOneByIsik(Isik isik);
}
