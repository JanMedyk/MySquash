package pl.coderslab.squash.User.register.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.squash.User.register.OnRegistrationCompleteEvent;
import pl.coderslab.squash.model.Token;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.User.register.validators.PassValidator;
import pl.coderslab.squash.User.service.UserService;
import pl.coderslab.squash.User.register.validators.UniqueMailValidator;
import pl.coderslab.squash.User.register.validators.UniqueValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/user")
@AllArgsConstructor

public class RegisterController {
    @Autowired
    ApplicationEventPublisher eventPublisher;

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
    public String register(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {

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
            String appUrl=request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,request.getLocale(),appUrl));


            return "/user/confirmEmail";
            }

//        }

    }
    @RequestMapping("/registrationConfirm")

    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token)
    {
        Locale locale=request.getLocale();
        Token token1=userService.getToken(token);
//        if(token1==null)
//        {
//            String messeage=
//        }
        User user=token1.getUser();

        userService.saveUserWithoutHas(user);
        return "user/registerConfirm";
    }
//    Wynieś do innego controllera
    @GetMapping("/user/all")
    public ModelAndView findAll(@AuthenticationPrincipal UserDetails customUser){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("all",userService.findAll());
        modelAndView.setViewName("user/all");
        return modelAndView;

    }


}
