package pl.coderslab.squash.Level.service;

import pl.coderslab.squash.model.Level;
import pl.coderslab.squash.model.LevelEnum;
import pl.coderslab.squash.model.Sport;

import java.util.List;

public interface LevelService {

    List<Level> findAll();
    Level findByName(LevelEnum name);




}
