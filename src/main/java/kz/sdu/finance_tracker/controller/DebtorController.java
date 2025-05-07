package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.dto.DebtorDto;
import kz.sdu.finance_tracker.service.DebtorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debtor")
public class DebtorController {

    private final DebtorService debtorService;


    @GetMapping
    public ResponseEntity<Page<DebtorDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(debtorService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<DebtorDto> save(@RequestBody DebtorDto debtorDto) {
        return ResponseEntity.ok(debtorService.create(debtorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebtorDto> update(@PathVariable Long id,
                                            @RequestBody DebtorDto debtorDto) {
        return ResponseEntity.ok(debtorService.update(id, debtorDto));
    }

    @PostMapping("/set-payed")
    public void payed(Long id) {
        debtorService.payedTrue(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id) {
        debtorService.deleteById(id);
    }
}
