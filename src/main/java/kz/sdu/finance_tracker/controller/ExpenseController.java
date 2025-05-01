package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.dto.ExpenseDto;
import kz.sdu.finance_tracker.entity.Expense;
import kz.sdu.finance_tracker.repository.ExpenseRepository;
import kz.sdu.finance_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<Page<ExpenseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(expenseService.findAll(pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> findAllByFilter(@RequestParam String from,
                                                         @RequestParam String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return ResponseEntity.ok(expenseService.findAllByFilter(fromDate, toDate));
    }

    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody ExpenseDto expenseDto) {
        return ResponseEntity.ok(expenseService.save(expenseDto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<Expense> updateById(@PathVariable Long id, @RequestBody ExpenseDto expenseDto) {
        return ResponseEntity.ok(expenseService.updateById(id, expenseDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        expenseService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
