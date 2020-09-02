package pl.coderslab.squash.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.squash.model.Role;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.service.UserService;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class userServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public userServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public void saveUser(@Valid User user) {
//        Pattern.compile("")
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
}
