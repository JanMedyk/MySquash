package pl.coderslab.squash.MatchHistory.validators;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.coderslab.squash.MatchHistory.service.MatchHistoryService;
import pl.coderslab.squash.model.MatchHistory;
import pl.coderslab.squash.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UniqueDateValidator implements Validator {
    private final MatchHistoryService matchHistoryService;

    public UniqueDateValidator(MatchHistoryService matchHistoryService) {
        this.matchHistoryService = matchHistoryService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MatchHistory.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        MatchHistory matchHistory= (MatchHistory) o;
        LocalDate localDate=LocalDate.parse(matchHistory.getDateMatch());
        LocalTime localTime=matchHistory.getTimeMatch();
        User user=matchHistory.getUserPrzyjmujacy();



        if(matchHistoryService.findByDateMatchTotalAndUserPrzyjmujacy((LocalDateTime.of(localDate,localTime)),user)!=null)
        {
            errors.rejectValue("timeMatch","matchHistory.timeMatch","masz juz inne spotkanie ");
        }


    }
}
