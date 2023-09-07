package code.urmas.yritus.service;

import code.urmas.yritus.model.Tyyp;
import code.urmas.yritus.repository.TyypRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TyypService {

    @Autowired
    private TyypRepository tyypRepository;
    public List<Tyyp> listAll() {
        return tyypRepository.findAll();
    }
    public Tyyp getById(Long i) {
        return tyypRepository.findById(i).get();
    }
}
