package kz.sdu.finance_tracker.repository;

import kz.sdu.finance_tracker.entity.Debtor;
import kz.sdu.finance_tracker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, Long> {

    Page<Debtor> findAllByUserAndPayedFalse(User user, Pageable pageable);
}
