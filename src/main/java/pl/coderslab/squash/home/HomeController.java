package pl.coderslab.squash.home;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.squash.Sport.repository.SportRepository;
import pl.coderslab.squash.Sport.service.SportService;
import pl.coderslab.squash.User.repository.RoleRepository;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.model.Role;
import pl.coderslab.squash.model.Sport;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.model.levelEnum;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
private final SportRepository sportRepository;
private final RoleRepository roleRepository;
private final SportService sportService;
private final UserService userService;

    @GetMapping("/")
    public String home(){
        sportService.saveSport(new Sport("PingPong", levelEnum.Początkujacy));
        sportService.saveSport(new Sport("PingPong", levelEnum.Średniozaawansowany));
        sportService.saveSport(new Sport("PingPong", levelEnum.Zaawansowany));
        sportService.saveSport(new Sport("Squash",levelEnum.Początkujacy));
        sportService.saveSport(new Sport("Squash",levelEnum.Średniozaawansowany));
        sportService.saveSport(new Sport("Squash",levelEnum.Zaawansowany));
        sportService.saveSport(new Sport("Badbingom",levelEnum.Początkujacy));
        sportService.saveSport(new Sport("Badbingom",levelEnum.Zaawansowany));
        sportService.saveSport(new Sport("Badbingom",levelEnum.Średniozaawansowany));
        sportService.saveSport(new Sport("Tenis",levelEnum.Początkujacy));
        sportService.saveSport(new Sport("Tenis",levelEnum.Średniozaawansowany));
        sportService.saveSport(new Sport("Tenis",levelEnum.Zaawansowany));
        roleRepository.save(new Role("ROLE_USER"));

        User user=new User("jamnik1234@o2.pl","Jan","Bayo","Medyk","wrocław",10,"pies00",sportService.findAll());
        User user2=new User("jan.medyk97@gmail.com","Kasia","kasia","Medyk","wrocław",10,"pies000",sportService.findAll());
        userService.saveUser(user);
        userService.saveUser(user2);
        return "/home/home";

    }
}
