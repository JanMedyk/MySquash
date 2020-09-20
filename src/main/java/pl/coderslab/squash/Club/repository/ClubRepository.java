package pl.coderslab.squash.Club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.Club;
@Repository
public interface ClubRepository  extends JpaRepository<Club, Long> {
}
