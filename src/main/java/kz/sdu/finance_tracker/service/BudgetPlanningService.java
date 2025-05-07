package kz.sdu.finance_tracker.service;

import kz.sdu.finance_tracker.dto.BudgetPlanningDto;
import kz.sdu.finance_tracker.entity.BudgetPlanning;
import kz.sdu.finance_tracker.mapper.BudgetPlanningMapper;
import kz.sdu.finance_tracker.repository.BudgetPlanningRepository;
import kz.sdu.finance_tracker.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetPlanningService {

    private final BudgetPlanningRepository budgetPlanningRepository;
    private final BudgetPlanningMapper budgetPlanningMapper;

    @Transactional
    public BudgetPlanning save(BudgetPlanningDto budgetPlanningDto) {
        BudgetPlanning budgetPlanning = new BudgetPlanning();
        budgetPlanning.setNameRu(budgetPlanningDto.getNameRu());
        budgetPlanning.setUser(SecurityUtils.getCurrentUser());
        budgetPlanning.setSource(budgetPlanningDto.getSource());
        budgetPlanning.setPlanSum(budgetPlanningDto.getPlanSum());
        budgetPlanning.setCurrentSum(budgetPlanningDto.getCurrentSum());
        double l = (budgetPlanningDto.getCurrentSum() * 100) / budgetPlanningDto.getPlanSum();
        budgetPlanning.setPercentage(l);

        return budgetPlanningRepository.save(budgetPlanning);
    }

    @Transactional
    public BudgetPlanningDto updateById(Long id, BudgetPlanningDto budgetPlanningDto) {
        Optional<BudgetPlanning> byId = budgetPlanningRepository.findById(id);
        if (byId.isPresent()) {
            BudgetPlanning budgetPlanning = byId.get();
            Long total = budgetPlanning.getCurrentSum() + budgetPlanningDto.getCurrentSum();
            budgetPlanning.setCurrentSum(total);
            if (Objects.nonNull(budgetPlanningDto.getPlanSum())) {
                budgetPlanning.setPlanSum(budgetPlanningDto.getPlanSum());
            }
            if (Objects.nonNull(budgetPlanningDto.getNameRu())) {
                budgetPlanning.setNameRu(budgetPlanningDto.getNameRu());
            }

            double d = (total * 100) / budgetPlanning.getPlanSum();
            budgetPlanning.setPercentage(d);
            BudgetPlanning save = budgetPlanningRepository.save(budgetPlanning);

            return budgetPlanningMapper.toDto(save);
        }

        return null;
    }

    @Transactional
    public Page<BudgetPlanningDto> findAll(Pageable pageable) {
        return budgetPlanningRepository.findAll(pageable)
                .map(budgetPlanningMapper::toDto);
    }

    public void deleteById(Long id) {
        budgetPlanningRepository.deleteById(id);
    }
}
