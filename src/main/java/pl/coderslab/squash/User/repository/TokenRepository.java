package pl.coderslab.squash.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.squash.model.Token;
import pl.coderslab.squash.model.User;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Token findByToken(String token);
    Token findByUser(User user);

}
