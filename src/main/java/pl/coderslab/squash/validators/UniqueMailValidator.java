package pl.coderslab.squash.validators;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.service.UserService;
//@NoArgsConstructor
@AllArgsConstructor
public class UniqueMailValidator implements Validator{

    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;
        if (userService.findByMail(user.getMail())!=null) {
            errors.rejectValue("mail","user.mail","jest już użytkownik o takim mailu");
        }

    }
}
