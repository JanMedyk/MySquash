package pl.coderslab.squash.Sport.repository;

import org.springframework.stereotype.Service;
import pl.coderslab.squash.Sport.service.SportService;
import pl.coderslab.squash.model.Sport;

import java.util.List;

@Service
public class SportServiceImpl implements SportService {
    private final SportRepository sportRepository;

    public SportServiceImpl(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @Override
    public void saveSport(Sport sport) {
        sportRepository.save(sport);

    }

    @Override
    public List<Sport> findAll() {
        return sportRepository.findAll();
    }
}
