package kz.sdu.finance_tracker.repository;

import kz.sdu.finance_tracker.entity.BudgetPlanning;
import kz.sdu.finance_tracker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPlanningRepository extends JpaRepository<BudgetPlanning, Long> {

    Page<BudgetPlanning> findAllByUser(User user, Pageable pageable);

}
