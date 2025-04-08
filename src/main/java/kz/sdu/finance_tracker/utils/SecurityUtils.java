package kz.sdu.finance_tracker.utils;

import kz.sdu.finance_tracker.entity.User;
import kz.sdu.finance_tracker.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            PersonDetails personDetails = (PersonDetails) principal;
            return personDetails.getUser();
        } else {
            throw new IllegalArgumentException("User is not authorized");
        }
    }

}