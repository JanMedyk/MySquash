package pl.coderslab.squash.User.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.coderslab.squash.model.User;
import pl.coderslab.squash.service.UserService;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private UserService service;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createToken(user, token);
        String addres = user.getMail();
        String subject = "Registration Confirmation";
        String confirmationURL = event.getAppUrl() + "/user/registrationConfirm?token=" + token;
//        String message = messageSource.getMessage("message.regSucc", null,event.getLocale());
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(addres);
        email.setSubject(subject);
        email.setText("\r\n" + "http://localhost:8080" + confirmationURL);
        email.setFrom("jan.medyk97@gmail.com");

        mailSender.send(email);


    }
}
