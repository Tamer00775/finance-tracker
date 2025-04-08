package kz.sdu.finance_tracker.mapper;

import kz.sdu.finance_tracker.dto.BudgetPlanningDto;
import kz.sdu.finance_tracker.entity.BudgetPlanning;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetPlanningMapper {

    BudgetPlanningDto toDto(BudgetPlanning budgetPlanning);
}
