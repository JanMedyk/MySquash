package pl.coderslab.squash.app.MatchHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.MatchHistory;
import pl.coderslab.squash.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory,Long> {
    List<MatchHistory> findAllBy();
    MatchHistory findByDateMatchTotalAndUserPrzyjmujacy(LocalDateTime localDateTime, User user);



}
