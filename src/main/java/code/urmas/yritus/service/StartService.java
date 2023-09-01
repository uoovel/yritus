package code.urmas.yritus.service;

import code.urmas.yritus.model.Makseviis;
import code.urmas.yritus.model.Tyyp;
import code.urmas.yritus.repository.MakseviisRepository;
import code.urmas.yritus.repository.TyypRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StartService {
    @Autowired
    private TyypRepository tyypRepository;
    @Autowired
    private MakseviisRepository makseviisRepository;

    public void lisaAlgandmed(){
        Tyyp tyyp1 = new Tyyp();
        tyyp1.setNimetus("Eraisik");
        tyypRepository.save(tyyp1);
        Tyyp tyyp2 = new Tyyp();
        tyyp2.setNimetus("Ettevõte");
        tyypRepository.save(tyyp2);

        Makseviis makseviis1 = new Makseviis();
        makseviis1.setNimetus("Pangaülekanne");
        makseviisRepository.save(makseviis1);
        Makseviis makseviis2 = new Makseviis();
        makseviis2.setNimetus("Sularaha");
        makseviisRepository.save(makseviis2);
    }

}
