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
import pl.coderslab.squash.model.SportEnum;
import pl.coderslab.squash.model.User;

import javax.validation.Valid;
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

//    @RequestMapping("/searchEnemy")
//    public ModelAndView appSearchEnemy(@AuthenticationPrincipal CurrentUser currentUser) {
//        User user = currentUser.getUser();
////        Hibernate.initialize(user.getSports());
//        ModelAndView modelAndView = new ModelAndView();
//        List<User> users = userService.findAll();
//
//
//        users.remove(user);
//        users.removeIf(e -> !e.isEnabled());
//        modelAndView.addObject("users", users);
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("/app/searchEnemy");
//        return modelAndView;
//
//
//    }
//
//    @GetMapping("/wyzwijEnemy")
//    @ResponseBody
//    public ModelAndView appWyzwijEnemy(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("idEnemy") Long idEnemy) {
//        User you = currentUser.getUser();
//        MatchHistory matchHistory=new MatchHistory();
//
//        User enemyUser = userService.findById(idEnemy);
//        matchHistory.setUserPrzyjmujacy(enemyUser);
//        matchHistory.setUserZakladajacy(you);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("enemy", enemyUser);
//        modelAndView.addObject("you", you);
//        modelAndView.addObject("date",matchHistory);
////            modelAndView.addObject("sports", SportEnum);
//
//        modelAndView.setViewName("/app/wyzwijEnemy");
//
//        return modelAndView;
//    }
//
//    @PostMapping("/wyzwijEnemy")
//    public String appPostWyzwijEnemy(@AuthenticationPrincipal CurrentUser currentUser, @Valid MatchHistory date) {
//
//
//        return ;
//
//    }


}
