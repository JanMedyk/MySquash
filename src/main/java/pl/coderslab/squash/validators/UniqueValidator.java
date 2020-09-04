package pl.coderslab.squash.validators;


import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.service.UserService;

@AllArgsConstructor
public class UniqueValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;
        if (userService.findByUserName(user.getUserName())!=null) {
            errors.rejectValue("userName","user.userName","jest już użytkownik o takim username");
        }

    }
}
