package pl.coderslab.squash.home;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.squash.Sport.repository.SportRepository;
import pl.coderslab.squash.model.Sport;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
private final SportRepository sportRepository;
    @GetMapping("/")
    public String home(){

     sportRepository.save(new Sport("PingPong"));
      sportRepository.save(new Sport("Squash"));
       sportRepository.save(new Sport("Badbingom"));
      sportRepository.save(new Sport("Tenis"));

        return "/home/home";

    }
}
