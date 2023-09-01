package code.urmas.yritus.service;

import code.urmas.yritus.model.Eraisik;
import code.urmas.yritus.model.Isik;
import code.urmas.yritus.repository.EraisikRepository;
import code.urmas.yritus.repository.IsikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EraisikService {
    @Autowired
    EraisikRepository eraisikRepository;

    public Eraisik saveEraisik(Eraisik eraisik) {
        Eraisik savederaIsik = eraisikRepository.save(eraisik);
        return savederaIsik;
    }
}
