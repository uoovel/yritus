package code.urmas.yritus.service;

import code.urmas.yritus.model.Isik;
import code.urmas.yritus.repository.IsikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IsikService {
    @Autowired
    IsikRepository isikRepository;

    public Isik saveCustomer(Isik isik) {
        Isik savedIsik = isikRepository.save(isik);
        return savedIsik;
    }

    public Isik get(long id) {
        return isikRepository.findById(id).get();
    }
}
