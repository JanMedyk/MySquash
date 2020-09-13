package pl.coderslab.squash.Level.repository;

import org.springframework.stereotype.Service;
import pl.coderslab.squash.Level.service.LevelService;
import pl.coderslab.squash.Sport.service.SportService;
import pl.coderslab.squash.model.Level;
import pl.coderslab.squash.model.LevelEnum;
import pl.coderslab.squash.model.Sport;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;


    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }


    @Override
    public List<Level> findAll() {
        return levelRepository.findAllBy();
    }

    @Override
    public Level findByName(LevelEnum name) {
        return levelRepository.findByName(name);
    }

}
