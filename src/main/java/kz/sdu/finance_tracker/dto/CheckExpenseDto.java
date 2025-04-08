package kz.sdu.finance_tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class CheckExpenseDto {

    private Long id;
    private String title;
    private String checkUuid;
    private Instant createdDate;
}
