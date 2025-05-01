package kz.sdu.finance_tracker.mapper;

import kz.sdu.finance_tracker.dto.ExpenseDto;
import kz.sdu.finance_tracker.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(source = "category", target = "category")
    @Mapping(source = "user", target = "user")
    ExpenseDto toDto(Expense expense);

}
