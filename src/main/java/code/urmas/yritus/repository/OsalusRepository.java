package code.urmas.yritus.repository;

import code.urmas.yritus.model.Osalus;
import code.urmas.yritus.model.Yritus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OsalusRepository extends JpaRepository<Osalus, Long> {
//järg
    List<Osalus> findByYritus(Yritus yritus);

    void deleteByYritus(Yritus yritus);
}
