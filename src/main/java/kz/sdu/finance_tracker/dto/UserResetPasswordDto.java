package kz.sdu.finance_tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResetPasswordDto {

    private String password;
    private String newPassword;
    private String token;
}
