package pl.coderslab.squash.Club.service;

import org.springframework.stereotype.Service;
import pl.coderslab.squash.Club.repository.ClubRepository;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
}
