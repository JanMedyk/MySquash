package pl.coderslab.squash.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.validators.PassValidator;
import pl.coderslab.squash.service.UserService;
import pl.coderslab.squash.validators.UniqueMailValidator;
import pl.coderslab.squash.validators.UniqueValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@AllArgsConstructor

public class UserController {
    private final UserService userService;
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.addValidators(new PassValidator());
        binder.addValidators(new UniqueMailValidator(userService));
        binder.addValidators(new UniqueValidator(userService));
    }


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
