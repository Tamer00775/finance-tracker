package kz.sdu.finance_tracker.mapper;

import kz.sdu.finance_tracker.dto.CheckExpenseDto;
import kz.sdu.finance_tracker.entity.CheckExpense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckExpenseMapper {

    CheckExpenseDto toDto(CheckExpense checkExpense);
}
