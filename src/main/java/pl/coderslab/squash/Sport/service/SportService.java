package pl.coderslab.squash.Sport.service;

import pl.coderslab.squash.model.Sport;

import java.util.List;

public interface SportService {
    void saveSport(Sport sport);
    List<Sport> findAll();
    List<Sport> findAllWithUser();


}
