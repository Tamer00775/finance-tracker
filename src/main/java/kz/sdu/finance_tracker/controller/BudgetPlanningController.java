package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.dto.BudgetPlanningDto;
import kz.sdu.finance_tracker.entity.BudgetPlanning;
import kz.sdu.finance_tracker.service.BudgetPlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/budget-planning")
@RequiredArgsConstructor
@RestController
public class BudgetPlanningController {

    private final BudgetPlanningService budgetPlanningService;

    @GetMapping
    public ResponseEntity<Page<BudgetPlanningDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(budgetPlanningService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<BudgetPlanning> create(@RequestBody BudgetPlanningDto dto) {
        return ResponseEntity.ok(budgetPlanningService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetPlanningDto> update(@PathVariable Long id,
            @RequestBody BudgetPlanningDto dto) {
        return ResponseEntity.ok(budgetPlanningService.updateById(id, dto));
    }
}
