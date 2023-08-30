package code.urmas.yritus.service;

import code.urmas.yritus.model.Yritus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import code.urmas.yritus.repository.YritusRepository;

import java.util.List;

@Service
@Transactional
public class YritusService {
    @Autowired
    private YritusRepository yritusRepository;
    public List<Yritus> listAll() {
        return yritusRepository.findAll();
    }

    public void save(Yritus yritus) {
        yritusRepository.save(yritus);
    }

    public Yritus get(long id) {
        return yritusRepository.findById(id).get();
    }

    public void delete(long id) {
        yritusRepository.deleteById(id);
    }

}
