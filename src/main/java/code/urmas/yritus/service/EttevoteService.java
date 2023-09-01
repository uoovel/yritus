package code.urmas.yritus.service;


import code.urmas.yritus.model.Ettevote;
import code.urmas.yritus.repository.EttevoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EttevoteService {
    @Autowired
    EttevoteRepository ettevoteRepository;

    public Ettevote saveEttevote(Ettevote ettevote) {
        Ettevote savedEttevote = ettevoteRepository.save(ettevote);
        return savedEttevote;
    }
}
