package pl.coderslab.squash.app.Challenge;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.Sport.repository.SportRepository;
import pl.coderslab.squash.User.service.CurrentUser;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.app.MatchHistory.service.MatchHistoryService;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class challengeController {
    private final UserService userService;
    private final SportRepository sportRepository;
    private final MatchHistoryService matchHistoryService;
    @RequestMapping("/chalange")
    public ModelAndView appChalange(@AuthenticationPrincipal CurrentUser currentUser)

    {
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("/app/challenge");
            return modelAndView;


    }
    @RequestMapping("/challange/yoursChallange")
    public ModelAndView appYoursChallange(@AuthenticationPrincipal CurrentUser currentUser)
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("yoursChallange",matchHistoryService.findAllByUsernameZakladajacy(currentUser.getUser()));
        modelAndView.setViewName("/app/yoursChallange");
        return modelAndView;

    }
}
