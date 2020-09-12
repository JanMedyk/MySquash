package pl.coderslab.squash.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    User findByMail(String mail);
    List<User> findAllBy();
User findAllById(Long id);




}
