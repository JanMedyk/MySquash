package pl.coderslab.squash.User.service;

import pl.coderslab.squash.model.Token;
import pl.coderslab.squash.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

    User findByMail(String mail);

    List<User> findAll();

    //    List<User> findAllWithSports();
    User findById(Long id);

    void createToken(User user, String token);

    Token getToken(String token);

    void saveUserWithoutHas(User user);


}
