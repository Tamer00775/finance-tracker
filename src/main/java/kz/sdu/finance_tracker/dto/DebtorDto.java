package kz.sdu.finance_tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DebtorDto {

    private String fullName;
    private Double sum;
    private boolean isPayed;
    private String category;
    private LocalDateTime expiredDate;
    private String comment;
}
