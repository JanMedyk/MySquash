package pl.coderslab.squash.app.MatchHistory.service;

import org.springframework.stereotype.Service;
import pl.coderslab.squash.app.MatchHistory.repository.MatchHistoryRepository;
import pl.coderslab.squash.model.MatchHistory;
import pl.coderslab.squash.model.User;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MatchHistoryService {
    private final MatchHistoryRepository matchHistoryRepository;

    public MatchHistoryService(MatchHistoryRepository matchHistoryRepository) {
        this.matchHistoryRepository = matchHistoryRepository;
    }

    public void saveMatchHistory(MatchHistory matchHistory) {
        matchHistoryRepository.save(matchHistory);
    }

   public  List<MatchHistory> findAll() {

        return matchHistoryRepository.findAll();
    }
public MatchHistory findByDateMatchTotalAndUserPrzyjmujacy(LocalDateTime localDateTime, User user)
{
   return matchHistoryRepository.findByDateMatchTotalAndUserPrzyjmujacy(localDateTime,user);

}



}
