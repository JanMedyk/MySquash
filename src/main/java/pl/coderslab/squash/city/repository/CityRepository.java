package pl.coderslab.squash.city.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.City;
import pl.coderslab.squash.model.Sport;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    List<Sport> findAllBy();

}
