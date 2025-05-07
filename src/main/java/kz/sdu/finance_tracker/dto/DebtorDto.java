package kz.sdu.finance_tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DebtorDto {

    private Long id;
    private UserDto userDto;
    private String debtorFullName;
    private Double sum;
    private LocalDate expiredDate;
    private String notes;
    private boolean payed;
}
