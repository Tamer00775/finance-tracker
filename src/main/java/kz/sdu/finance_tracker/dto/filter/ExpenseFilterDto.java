package kz.sdu.finance_tracker.dto.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ExpenseFilterDto {

    private LocalDate from;
    private LocalDate to;
}
