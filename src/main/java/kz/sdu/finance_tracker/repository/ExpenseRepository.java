package kz.sdu.finance_tracker.repository;

import kz.sdu.finance_tracker.entity.Expense;
import kz.sdu.finance_tracker.entity.User;
import kz.sdu.finance_tracker.enums.OperationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.sum) FROM Expense e WHERE e.user = :user AND e.operationType = :operationType")
    Double getTotalSumByUserAndOperationType(@Param("user") User user, @Param("operationType") OperationType operationType);

    List<Expense> findAllByCreatedDateBetween(LocalDateTime from, LocalDateTime to, Sort sort);

    Page<Expense> findAllByUser(User user, Pageable pageable);
}
