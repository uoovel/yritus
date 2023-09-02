package code.urmas.yritus.service;

import code.urmas.yritus.model.*;
import code.urmas.yritus.repository.*;
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
    @Autowired
    private YritusRepository yritusRepository;
    @Autowired
    private IsikRepository isikRepository;
    @Autowired
    private EttevoteRepository ettevoteRepository;
    @Autowired
    private OsalusRepository osalusRepository;

    public void lisaAlgandmed(){
        Tyyp tyyp1 = new Tyyp();
        tyyp1.setNimetus("Eraisik");
        Tyyp tyyp1Saved = tyypRepository.save(tyyp1);
        Tyyp tyyp2 = new Tyyp();
        tyyp2.setNimetus("Ettevõte");
        Tyyp tyyp2Saved = tyypRepository.save(tyyp2);

        Makseviis makseviis1 = new Makseviis();
        makseviis1.setNimetus("Pangaülekanne");
        Makseviis makseviis1Saved = makseviisRepository.save(makseviis1);
        Makseviis makseviis2 = new Makseviis();
        makseviis2.setNimetus("Sularaha");
        Makseviis makseviis2Saved = makseviisRepository.save(makseviis2);

        Yritus yritus = new Yritus();
        yritus.setNimetus("Terminaatori kontsert");
        Yritus yritusSaved = yritusRepository.save(yritus);

        String juriidilineNimi = "ABB AS";
        Isik isik = new Isik();
        isik.setNimi(juriidilineNimi);
        isik.setTyyp(tyyp2Saved);
        Isik isikSaved = isikRepository.save(isik);
        Ettevote ettevote = new Ettevote();
        ettevote.setJuriidilinenimi(juriidilineNimi);
        ettevote.setIsik(isikSaved);
        Ettevote ettevoteSaved = ettevoteRepository.save(ettevote);

        Osalus osalus = new Osalus();
        osalus.setYritus(yritusSaved);
        osalus.setIsik(isikSaved);
        osalus.setMakseviis(makseviis2Saved);
        osalusRepository.save(osalus);
    }

}
