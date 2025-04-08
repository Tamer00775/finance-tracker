package kz.sdu.finance_tracker.repository;

import kz.sdu.finance_tracker.entity.CheckExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckExpenseRepository extends JpaRepository<CheckExpense, Long> {
}
