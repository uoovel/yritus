package code.urmas.yritus.repository;

import code.urmas.yritus.model.Osalus;
import code.urmas.yritus.model.Yritus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OsalusRepository extends JpaRepository<Osalus, Long> {
//järg
    List<Osalus> findByYritus(Yritus yritus);

    void deleteByYritus(Yritus yritus);

    @Query("SELECT sum(o.tulijatearv) from Osalus o where yritus=?1 ")
    int sumOsalejadByYritus(Yritus yritus);
}
