package ostap.storoshchuk.botscrew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ostap.storoshchuk.botscrew.entity.Lector;
import ostap.storoshchuk.botscrew.repository.LectorRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class LectorService {

    @Autowired
    private LectorRepository lectorRepository;


    @Transactional
    public void saveLector(Lector lector) {
        lectorRepository.save(lector);
    }

    @Transactional
    public List<Lector> findAllLectors(){
       return lectorRepository.findAll();
    }

    @Transactional
    public Optional<Lector> findLectorById(Long id) {
        return lectorRepository.findById(id);
    }


}
