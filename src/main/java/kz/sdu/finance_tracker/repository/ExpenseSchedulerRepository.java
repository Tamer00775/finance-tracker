package kz.sdu.finance_tracker.repository;

import kz.sdu.finance_tracker.entity.ExpenseScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseSchedulerRepository extends JpaRepository<ExpenseScheduler, Long> {
}
