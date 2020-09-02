package pl.coderslab.squash.validators;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.coderslab.squash.model.User;

public class PassValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user= (User) o;
        if(user.getPassword().length()>20)
        {
            errors.rejectValue("password","user.pass","password is to long");
        }
        else if(user.getPassword().length()<3)
        {
            errors.rejectValue("password","user.pass","password is to short");
        }

    }
}
