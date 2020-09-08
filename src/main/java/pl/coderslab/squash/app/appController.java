package pl.coderslab.squash.app;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.User.service.CurrentUser;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.model.User;

@Controller
@RequestMapping("/app")
@AllArgsConstructor

public class appController {
    private final UserService userService;

    @RequestMapping("/")
    public ModelAndView appHome(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/app/home");
        return modelAndView;


    }

    @RequestMapping("/")
    public ModelAndView appSearchEnemy(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("users",userService.findAll());



    }


}
