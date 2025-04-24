package kz.sdu.finance_tracker.dto;

import kz.sdu.finance_tracker.enums.OperationType;
import kz.sdu.finance_tracker.enums.ScheduleType;
import kz.sdu.finance_tracker.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExpenseDto {

    private TransactionType transactionType;
    private OperationType operationType;
    private Double sum;
    private String categoryCode;
    private String comment;
    private boolean scheduled;
    private ScheduleType scheduleType;

}
