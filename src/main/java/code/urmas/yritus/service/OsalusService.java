package code.urmas.yritus.service;

import code.urmas.yritus.dto.OsalusDto;
import code.urmas.yritus.model.*;
import code.urmas.yritus.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OsalusService {
    @Autowired
    private OsalusRepository osalusRepository;
    @Autowired
    private IsikRepository isikRepository;
    @Autowired
    private EraisikRepository eraisikRepository;
    @Autowired
    private EttevoteRepository ettevoteRepository;
    @Autowired
    private YritusRepository yritusRepository;
    @Autowired
    private MakseviisRepository makseviisRepository;

    public void saveOsalus(Osalus osalus) {
        osalusRepository.save(osalus);
    }

    public List<Osalus> getOsalussByYritus(Yritus yritus) {
        return osalusRepository.findByYritus(yritus);
    }

    public void deleteAllByYritus(Yritus yritus) {
        osalusRepository.deleteByYritus(yritus);
    }

    public void delete(Long id) {
        osalusRepository.deleteById(id);
    }

    public Osalus get(long id) {
        return osalusRepository.findById(id).get();
    }

    public int summOsalejad(Yritus yritus) {
        return osalusRepository.sumOsalejadByYritus(yritus);
    }

    public void salvestaOsalus(Boolean oneraisik, Boolean onettevote, Boolean olemasolev, Isik isik, @Valid OsalusDto osalus, Tyyp tyyp, Isik isikSaved) {
        Integer osalejateArv = null;
        if(oneraisik && !olemasolev){
            Eraisik eraisik = new Eraisik();
            isik.setNimi(osalus.getEesnimi() + " " + osalus.getPerekonnanimi());
            isik.setKood(osalus.getIsikukood());
            isik.setTyyp(tyyp);
            isikSaved = isikRepository.save(isik);
            eraisik.setIsik(isikSaved);
            eraisik.setIsikukood(osalus.getIsikukood());
            eraisik.setEesnimi(osalus.getEesnimi());
            eraisik.setPerekonnanimi(osalus.getPerekonnanimi());
            Eraisik savedEraisik = eraisikRepository.save(eraisik);
        }
        if(onettevote && !olemasolev){
            Ettevote ettevote = new Ettevote();
            isik.setNimi(osalus.getJuriidilinenimi());
            isik.setKood(osalus.getRegistrikood());
            isik.setTyyp(tyyp);
            isikSaved = isikRepository.save(isik);
            ettevote.setIsik(isikSaved);
            ettevote.setJuriidilinenimi(osalus.getJuriidilinenimi());
            ettevote.setRegistrikood(osalus.getRegistrikood());
            Ettevote savedEttevote = ettevoteRepository.save(ettevote);
        }

        if(osalus.getIsikutyypid() == 1){
            osalejateArv = 1;
        }
        if(osalus.getIsikutyypid() == 2){
            osalejateArv = osalus.getTulijatearv();
        }

        Long yritusId = osalus.getYritusid();
        Yritus yritus = yritusRepository.findById(yritusId).get();
        Makseviis makseviis = makseviisRepository.findById(osalus.getMakseviisid()).get();
        String lisainfo = osalus.getLisainfo();
        Osalus osalusSalvestatav = new Osalus();
        osalusSalvestatav.setYritus(yritus);
        osalusSalvestatav.setIsik(isikSaved);
        osalusSalvestatav.setMakseviis(makseviis);
        osalusSalvestatav.setTulijatearv(osalejateArv);
        osalusSalvestatav.setLisainfo(lisainfo);
        osalusRepository.save(osalusSalvestatav);
    }
}
