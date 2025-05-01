package kz.sdu.finance_tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDto {

    private Long id;
    private String code;
    private String ru;
    private String kk;
    private String type;
}
