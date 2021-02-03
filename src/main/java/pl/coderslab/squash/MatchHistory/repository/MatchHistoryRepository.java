package pl.coderslab.squash.MatchHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.MatchHistory;
import pl.coderslab.squash.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {
    List<MatchHistory> findAllBy();

    MatchHistory findByDateMatchTotalAndUserPrzyjmujacy(LocalDateTime localDateTime, User user);

    @Query("select u from MatchHistory  u where u.userPrzyjmujacy=?1 or u.userZakladajacy=?1")
    List<MatchHistory> findAllByUserZakladajacyOrUserPrzyjmujacy(User user);
    List<MatchHistory> findAllByUserZakladajacy(User user);


    @Query("select u from MatchHistory  u where u.userZakladajacy=?1 or u.userPrzyjmujacy=?1 and u.id=?2 ")
    MatchHistory findAllByUserZakladajacyOrUserPrzyjmujacyAndId(User user, Long id);

    @Query("select u from MatchHistory  u where u.userZakladajacy=?1 and u.id=?2")
    MatchHistory findAllByUserZakladajacyAndId(User user, Long id);

    List<MatchHistory> findAllByUserPrzyjmujacy(User user);
MatchHistory findByUserPrzyjmujacyAndId(User user,Long id);

List<MatchHistory> findAllByUserPrzyjmujacyAndAcceptedMatch(User user,Boolean aa);
@Query("select u from MatchHistory  u where u.userPrzyjmujacy=?1 or u.userZakladajacy=?1 and u.dateMatchTotal < ?2 ")
    List<MatchHistory> findAllByUserZakladajacyOrUserPrzyjmujacyAndDateMatch(User user, LocalDateTime localDateTime);


}
