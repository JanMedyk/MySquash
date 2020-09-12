package pl.coderslab.squash.app;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.Sport.repository.SportRepository;
import pl.coderslab.squash.User.service.CurrentUser;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.model.MatchHistory;
import pl.coderslab.squash.model.Sport;
import pl.coderslab.squash.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
@AllArgsConstructor

public class appController {
    private final UserService userService;
    private final SportRepository sportRepository;


    @RequestMapping("")
    public ModelAndView appHome(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/app/home");
        return modelAndView;


    }
//    @RequestMapping("/searchEnemy")
//    public ModelAndView appSearchEnemy(@AuthenticationPrincipal CurrentUser currentUser)
//    {
//
//    }

    @RequestMapping("/searchEnemy")
    public ModelAndView appSearchEnemy(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
//        Hibernate.initialize(user.getSports());
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();


        users.remove(user);
        modelAndView.addObject("users", users);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/app/searchEnemy");
        return modelAndView;


    }

    @GetMapping("/wyzwijEnemy")
    @ResponseBody
    public ModelAndView appWyzwijEnemy(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("idEnemy") Long idEnemy) {
User you=currentUser.getUser();

            User enemyUser =userService.findById(idEnemy);
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.addObject("enemy",enemyUser);
            modelAndView.addObject("you",you);
            modelAndView.addObject("date",new MatchHistory());
//            modelAndView.addObject("sports",sportRepository.findAll().stream().forEach();distinct().collect(Collectors.toList()));

            modelAndView.setViewName("/app/wyzwijEnemy");

        return modelAndView;
    }
//    @PostMapping("/wyzwijEnemy")
//    public String appPostWyzwijEnemy()


}
