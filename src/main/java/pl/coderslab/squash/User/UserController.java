package pl.coderslab.squash.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.repository.UserRepository;
import pl.coderslab.squash.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@AllArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());

        modelAndView.setViewName("user/register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
           return "user/register";
        } else {
//            User user1 = userService.findByUserName(user.getUserName());
//            if (user1 != null) {
//
//            }
//            User user2 = userService.findByMail(user.getMail());
//            if (user2 != null) {
            userService.saveUser(user);
            return "redirect:/";
            }

//        }
    }
    @GetMapping("/users/all")
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("all",userService.findAll());
        modelAndView.setViewName("user/all");
        return modelAndView;

    }


}
