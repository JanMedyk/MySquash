package pl.coderslab.squash.User.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class LoginController {
@RequestMapping("/login")
    public ModelAndView login()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject(new User());
        modelAndView.setViewName("/user/login");
        return modelAndView;

    }

}
