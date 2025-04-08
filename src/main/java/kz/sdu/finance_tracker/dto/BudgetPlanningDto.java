package kz.sdu.finance_tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BudgetPlanningDto {

    private Long id;
    private String nameRu;
    private Long planSum;
    private Long currentSum;
    private LocalDate expirationDate;
    private String source;
    private Double percentage;
}
