package pl.coderslab.squash.service;

import pl.coderslab.squash.model.Token;
import pl.coderslab.squash.model.User;

import java.util.List;

public interface UserService {

    User findByUserName(String name);
    void saveUser(User user);
    User findByMail(String mail);
    List<User> findAll();

    void createToken(User user, String token);
    Token getToken(String token);
void saveUserWithoutHas(User user);



}
