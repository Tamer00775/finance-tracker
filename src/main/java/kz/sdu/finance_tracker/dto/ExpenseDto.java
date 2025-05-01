package kz.sdu.finance_tracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.sdu.finance_tracker.enums.OperationType;
import kz.sdu.finance_tracker.enums.ScheduleType;
import kz.sdu.finance_tracker.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ExpenseDto {

    @JsonFormat(pattern = "HH:mm:ss dd.MM.yyyy")
    private LocalDateTime createdDate;
    private TransactionType transactionType;
    private OperationType operationType;
    private Double sum;
    private CategoryDto category;
    private UserDto user;
    private String comment;
    private boolean scheduled;
    private ScheduleType scheduleType;

}
