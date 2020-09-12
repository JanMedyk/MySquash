package pl.coderslab.squash.User.repository;

import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.squash.model.Role;
import pl.coderslab.squash.model.Sport;
import pl.coderslab.squash.model.Token;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.User.service.UserService;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Service
@Transactional
public class userServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public userServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder,TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository=tokenRepository;
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public void saveUser(@Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole=roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

    }

    @Override
    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
       return entityManager.find(User.class, id);

    }


//    @Override
//    public List<User> findAllWithSports() {
//        List<User> list = findAll();
//        Map<Long, List<Sport>> sports = new HashMap<>();
//        Hibernate.initialize(list.forEach(e->sports.put(e.getId(),e.getSports())));
//        Hibernate.initialize();
//
//
//    }

    @Override
    public void createToken(User user, String token) {
            Token myToken=new Token(token,user);
            tokenRepository.save(myToken);

    }

    @Override
    public Token getToken(String token) {
        return tokenRepository.findByToken(token) ;

    }

    @Override
    public void saveUserWithoutHas(User user) {
        user.setEnabled(true);

        userRepository.save(user);

    }
}
