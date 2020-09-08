package pl.coderslab.squash.Sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.Sport;

import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport,Long> {
    List<Sport> findAllBy();

}
