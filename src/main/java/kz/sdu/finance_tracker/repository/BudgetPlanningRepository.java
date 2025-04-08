package kz.sdu.finance_tracker.repository;

import kz.sdu.finance_tracker.entity.BudgetPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPlanningRepository extends JpaRepository<BudgetPlanning, Long> {


}
