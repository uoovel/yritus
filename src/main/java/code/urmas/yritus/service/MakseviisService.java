package code.urmas.yritus.service;


import code.urmas.yritus.model.Makseviis;
import code.urmas.yritus.model.Yritus;
import code.urmas.yritus.repository.MakseviisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MakseviisService {
    @Autowired
    private MakseviisRepository makseviisRepository;
    public List<Makseviis> listAll() {
        return makseviisRepository.findAll();
    }

    public Makseviis get(long id) {
        return makseviisRepository.findById(id).get();
    }
}
