package code.urmas.yritus.repository;


import code.urmas.yritus.model.Ettevote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EttevoteRepository extends JpaRepository<Ettevote, Long> {
}
