package code.urmas.yritus.service;

import code.urmas.yritus.model.Osalus;
import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.repository.OsalusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OsalusService {
    @Autowired
    private OsalusRepository osalusRepository;

    public void saveOsalus(Osalus osalus) {
        osalusRepository.save(osalus);
    }

    public List<Osalus> getOsalussByYritus(Yritus yritus) {
        return osalusRepository.findByYritus(yritus);
    }
}
