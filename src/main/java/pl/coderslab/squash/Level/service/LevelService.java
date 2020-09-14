package pl.coderslab.squash.Level.service;

import org.springframework.stereotype.Service;
import pl.coderslab.squash.Level.repository.LevelRepository;
import pl.coderslab.squash.model.Level;
import pl.coderslab.squash.model.enums.LevelEnum;

@Service
public class LevelService {
        private final LevelRepository levelRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }
        public Level findByName(LevelEnum name)
        {
             return levelRepository.findByName(name);

        }




}
