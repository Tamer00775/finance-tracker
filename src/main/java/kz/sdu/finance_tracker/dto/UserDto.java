package kz.sdu.finance_tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String fullName;
    private String email ;
    private Double sum;
    private Double totalIncome;
    private Double totalOutcome;
}
