package pl.coderslab.squash.app.Challenge;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.Sport.repository.SportRepository;
import pl.coderslab.squash.User.service.CurrentUser;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.MatchHistory.repository.MatchHistoryRepository;
import pl.coderslab.squash.MatchHistory.service.MatchHistoryService;
import pl.coderslab.squash.model.MatchHistory;

import java.util.List;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class challengeController {
    private final UserService userService;
    private final SportRepository sportRepository;
    private final MatchHistoryService matchHistoryService;
    private final MatchHistoryRepository matchHistoryRepository;

    @RequestMapping("/challange")
    public ModelAndView appChalange(@AuthenticationPrincipal CurrentUser currentUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/app/challenge");
        return modelAndView;


    }

    @RequestMapping("/challange/yoursChallange")
    public ModelAndView appYoursChallange(@AuthenticationPrincipal CurrentUser currentUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("yoursChallange", matchHistoryService.findAllByUsernameZakladajacy(currentUser.getUser()));
        modelAndView.setViewName("/app/yoursChallange");
        return modelAndView;

    }

    @RequestMapping("/challange/yourChallange/deleteChallange")
    public String appDeleteChallange(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("Match") Long id) {

        MatchHistory matchHistory = matchHistoryRepository.findAllByUserZakladajacyAndId(currentUser.getUser(), id);

        matchHistoryRepository.delete(matchHistory);
        return "redirect:/app";


    }

    @RequestMapping("challange/waitingChallange")
    public ModelAndView appWaitnigChallange(@AuthenticationPrincipal CurrentUser currentUser) {
       List< MatchHistory> matchHistory = matchHistoryRepository.findAllByUserPrzyjmujacy(currentUser.getUser());
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.addObject("matches",matchHistory);
       modelAndView.setViewName("/app/waitingChallange");
       return modelAndView;


    }
    @RequestMapping("challange/waitingChallange/acctept")
    public String appAcceptChallange(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("Match") Long id,@RequestParam("accept") Boolean aacept)

    {
        MatchHistory matchHistory=matchHistoryRepository.findByUserPrzyjmujacyAndId(currentUser.getUser(),id);
        matchHistory.setAccepted(aacept);
        matchHistoryRepository.save(matchHistory);
        return "redirect:/app";



    }
}
