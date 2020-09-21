package pl.coderslab.squash.MatchHistory;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.coderslab.squash.Club.repository.ClubRepository;
import pl.coderslab.squash.MatchHistory.repository.MatchHistoryRepository;
import pl.coderslab.squash.MatchHistory.validators.UniqueDateEnemyValidator;
import pl.coderslab.squash.Sport.repository.SportRepository;
import pl.coderslab.squash.User.service.CurrentUser;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.MatchHistory.service.MatchHistoryService;
import pl.coderslab.squash.MatchHistory.validators.UniqueDateValidator;
import pl.coderslab.squash.model.MatchHistory;
import pl.coderslab.squash.model.Sets;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.model.enums.SportEnum;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class MatchHistoryController {


    private final UserService userService;
    private final SportRepository sportRepository;
    private final MatchHistoryService matchHistoryService;
    private final MatchHistoryRepository matchHistoryRepository;
    private final ClubRepository clubRepository;

    @InitBinder("matchHistory")
    public void initBinder(WebDataBinder binder) {

        binder.setValidator(new UniqueDateValidator(matchHistoryService));
        binder.setValidator(new UniqueDateEnemyValidator(matchHistoryService));
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
        modelAndView.addObject("matchHistory", matchHistory);
        modelAndView.addObject("clubs", clubRepository.findAll());

//            modelAndView.addObject("sports", SportEnum);

        modelAndView.setViewName("/app/wyzwijEnemy");

        return modelAndView;
    }

    @PostMapping("/wyzwijEnemy")
    public ModelAndView appPostWyzwijEnemy(RedirectAttributes attr, @Valid MatchHistory matchHistory, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
//            attr.addFlashAttribute("timeMatch",bindingResult);
//            attr.addFlashAttribute("matchHistory",matchHistory);
            modelAndView.addObject("matchHistory", matchHistory);
            modelAndView.addObject("enemy", matchHistory.getUserPrzyjmujacy());
            modelAndView.addObject("you", matchHistory.getUserZakladajacy());
            modelAndView.addObject("clubs", clubRepository.findAll());
            modelAndView.setViewName("/app/wyzwijEnemy");
            return modelAndView;
        } else {
            matchHistory.setAcceptedMatch(null);
            matchHistory.setCompleted(false);

            matchHistoryService.saveMatchHistory(matchHistory);
//            User user=matchHistory.getUserZakladajacy();
//            List<MatchHistory> matchHistories=user.getMatchHistories();
//            matchHistories.add(matchHistory);
//            user.setMatchHistories(matchHistories);
//            userService.saveUser(user);
            modelAndView.setViewName("redirect:/app");
            return modelAndView;

        }
    }

    @GetMapping("/matchHistory")
    public ModelAndView matchHistory(@AuthenticationPrincipal CurrentUser
                                             Cuser) {
        User user = Cuser.getUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("matches", matchHistoryRepository.findAllByUserZakladajacyOrUserPrzyjmujacy(user));

        modelAndView.setViewName("/app/matchHistory");
        return modelAndView;
    }

    @GetMapping("/matchHistory/completMatch")
    public ModelAndView CompletMatchHistory(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("Match") Long matchHistoryId) {
        User user = currentUser.getUser();
        MatchHistory matchHistory = matchHistoryService.findAllByUserZakladajacyOrUserPrzyjmujacyAndId(user, matchHistoryId);
        if (matchHistory.getSets().isEmpty()) {
            ArrayList<Sets> sets = new ArrayList<>();

            sets.add(0, new Sets());
            sets.add(1, new Sets());
            sets.add(2, new Sets());
            sets.add(3, new Sets());
            sets.add(4, new Sets());
            matchHistory.setSets(sets);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("matchToComplete", matchHistory);
//        modelAndView.addObject("sets",sets);
        modelAndView.setViewName("/app/CompleteMatch");
        return modelAndView;

    }

    @PostMapping("/matchHistory/completMatch")
    public ModelAndView PostCompletMatchHistory(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute("matchToComplete") MatchHistory matchToComplete) {
        ModelAndView modelAndView = new ModelAndView();
        AtomicReference<Integer> setsWinsUserPrzyjmujacy = new AtomicReference<>(0);
        AtomicReference<Integer> setsWinsUserZakladajacy = new AtomicReference<>(0);

        User user = currentUser.getUser();
        List<Sets> sets = matchToComplete.getSets();
        sets.removeIf(e -> e.getPktZakladajacy() == null);
        sets.removeIf(e -> e.getPktPrzyjmujacy() == null);
        sets.removeIf(e -> e.getPktPrzyjmujacy() == null);

        sets.stream().forEach(e ->
        {
            if (e.getPktPrzyjmujacy() > e.getPktZakladajacy()) {
                e.setUserWygrany(matchToComplete.getUserPrzyjmujacy());
                setsWinsUserPrzyjmujacy.getAndSet(setsWinsUserPrzyjmujacy.get() + 1);


            } else
                e.setUserWygrany(matchToComplete.getUserZakladajacy());
            setsWinsUserZakladajacy.getAndSet(setsWinsUserZakladajacy.get() + 1);

        });
        matchToComplete.setSets(sets);
        if (setsWinsUserPrzyjmujacy.get() > setsWinsUserZakladajacy.get()) {
            matchToComplete.setUserWinner(matchToComplete.getUserPrzyjmujacy());
        } else {
            matchToComplete.setUserWinner(matchToComplete.getUserZakladajacy());
        }
//        MatchHistory matchHistory = matchHistoryService.findAllByUserZakladajacyOrUserPrzyjmujacyAndId(user, matchToComplete.getId());
        matchToComplete.setCompleted(true);
        matchToComplete.setAcceptedResult(null);
        matchToComplete.setWhoCompleted(currentUser.getUser());
        matchHistoryService.saveMatchHistory(matchToComplete);

        modelAndView.setViewName("/app/home");
        return modelAndView;
    }
}
