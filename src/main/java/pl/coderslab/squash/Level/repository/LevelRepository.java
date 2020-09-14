package pl.coderslab.squash.Level.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.Level;
import pl.coderslab.squash.model.enums.LevelEnum;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level,Long> {
    List<Level> findAllBy();
    Level findByName(LevelEnum name);


}
