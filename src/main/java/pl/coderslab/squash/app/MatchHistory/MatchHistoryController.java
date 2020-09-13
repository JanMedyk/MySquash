package pl.coderslab.squash.app.MatchHistory;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.Sport.repository.SportRepository;
import pl.coderslab.squash.User.register.validators.PassValidator;
import pl.coderslab.squash.User.register.validators.UniqueMailValidator;
import pl.coderslab.squash.User.register.validators.UniqueValidator;
import pl.coderslab.squash.User.service.CurrentUser;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.app.MatchHistory.repository.MatchHistoryRepository;
import pl.coderslab.squash.app.MatchHistory.service.MatchHistoryService;
import pl.coderslab.squash.app.MatchHistory.validators.UniqueDateValidator;
import pl.coderslab.squash.model.MatchHistory;
import pl.coderslab.squash.model.User;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class MatchHistoryController {


    private final UserService userService;
    private final SportRepository sportRepository;
    private final MatchHistoryService matchHistoryService;

    @InitBinder("matchHistory")
    public void initBinder(WebDataBinder binder) {

        binder.setValidator(new UniqueDateValidator(matchHistoryService));
    }

    @RequestMapping("/searchEnemy")
    public ModelAndView appSearchEnemy(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
//        Hibernate.initialize(user.getSports());
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();


        users.remove(user);
        users.removeIf(e -> !e.isEnabled());
        modelAndView.addObject("users", users);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/app/searchEnemy");
        return modelAndView;


    }

    @GetMapping("/wyzwijEnemy")
    @ResponseBody
    public ModelAndView appWyzwijEnemy(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("idEnemy") Long idEnemy) {
        User you = currentUser.getUser();
        MatchHistory matchHistory = new MatchHistory();

        User enemyUser = userService.findById(idEnemy);
        matchHistory.setUserPrzyjmujacy(enemyUser);
        matchHistory.setUserZakladajacy(you);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("enemy", enemyUser);
        modelAndView.addObject("you", you);
        modelAndView.addObject("date", matchHistory);
//            modelAndView.addObject("sports", SportEnum);

        modelAndView.setViewName("/app/wyzwijEnemy");

        return modelAndView;
    }

    @PostMapping("/wyzwijEnemy")
    public String appPostWyzwijEnemy(@AuthenticationPrincipal CurrentUser currentUser, @Valid MatchHistory matchHistory, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/app/wyzwijEnemy";
        else {
            matchHistoryService.saveMatchHistory(matchHistory);
            return "/app/home";

        }
    }
}
