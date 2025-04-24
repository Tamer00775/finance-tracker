package kz.sdu.finance_tracker.service;

import kz.sdu.finance_tracker.dto.ExpenseDto;
import kz.sdu.finance_tracker.entity.Expense;
import kz.sdu.finance_tracker.entity.ExpenseScheduler;
import kz.sdu.finance_tracker.entity.User;
import kz.sdu.finance_tracker.enums.OperationType;
import kz.sdu.finance_tracker.enums.ScheduleType;
import kz.sdu.finance_tracker.repository.CategoryRepository;
import kz.sdu.finance_tracker.repository.ExpenseRepository;
import kz.sdu.finance_tracker.repository.ExpenseSchedulerRepository;
import kz.sdu.finance_tracker.repository.UserRepository;
import kz.sdu.finance_tracker.utils.SecurityUtils;
import liquibase.pro.packaged.E;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ExpenseSchedulerRepository expenseSchedulerRepository;

    @Transactional
    public Expense save(ExpenseDto expenseDto) {
        Expense expense = toEntity(expenseDto);
        User currentUser = SecurityUtils.getCurrentUser();
        Double sum = currentUser.getSum();
        if ((sum == null || sum == 0) && expenseDto.getOperationType().equals(OperationType.WITHDRAW)) {
            throw new IllegalArgumentException("No money");
        }
        if (expenseDto.getOperationType().equals(OperationType.DEPOSIT)) {
            currentUser.setSum(Optional.ofNullable(currentUser.getSum()).orElse(0.0) + expenseDto.getSum());
            userRepository.save(currentUser);
        } else {
            currentUser.setSum(currentUser.getSum() - expenseDto.getSum());
            userRepository.save(currentUser);
        }
        expense.setUser(currentUser);
        Expense save = expenseRepository.save(expense);
        if (expenseDto.isScheduled()) {
            createExpenseSchedule(save, expenseDto.getScheduleType());
        }

        return save;
    }

    private void createExpenseSchedule(Expense save, ScheduleType scheduleType) {
        ExpenseScheduler scheduler = new ExpenseScheduler();
        scheduler.setExpense(save);
        scheduler.setType(scheduleType.name());

        expenseSchedulerRepository.save(scheduler);
    }

    private Expense toEntity(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setCategory(
               categoryRepository.findByCode(expenseDto.getCategoryCode()).get()
        );
        expense.setOperationType(expenseDto.getOperationType());
        expense.setTransactionType(expenseDto.getTransactionType());
        expense.setCreatedDate(LocalDateTime.now());
        expense.setCreatedBy("system");
        expense.setSum(expenseDto.getSum());
        expense.setComment(expenseDto.getComment());

        return expense;
    }

    @Transactional
    public Expense updateById(Long id, ExpenseDto expenseDto) {
        Optional<Expense> byId = expenseRepository.findById(id);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Expense by Id is not present");
        }
        Expense expense = byId.get();
        Double oldSum = expense.getSum();
        OperationType oldOperationType = expense.getOperationType();
        expense.setTransactionType(expenseDto.getTransactionType());
        expense.setOperationType(expenseDto.getOperationType());
        expense.setSum(expenseDto.getSum());
        expense.setComment(expenseDto.getComment());
        expense.setCategory(categoryRepository.findByCode(expenseDto.getCategoryCode()).get());
        Expense save = expenseRepository.save(expense);
        User currentUser = SecurityUtils.getCurrentUser();
        if (!oldOperationType.equals(expenseDto.getOperationType())) {
            if (oldOperationType.equals(OperationType.DEPOSIT)) {
                Double sum = currentUser.getSum();
                sum = sum - oldSum;
                currentUser.setSum(sum);
            } else {
                Double sum = currentUser.getSum();
                sum = sum + oldSum;
                currentUser.setSum(sum + expenseDto.getSum());
            }
        } else {
            Double diff = currentUser.getSum() - expenseDto.getSum();
            if (diff > 0) {
                currentUser.setSum(currentUser.getSum() - diff);
            } else {
                currentUser.setSum(currentUser.getSum() + diff);
            }
        }
        userRepository.save(currentUser);

        return save;
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

    public Double getSum(User user, OperationType operationType) {
        return expenseRepository.getTotalSumByUserAndOperationType(user, operationType);
    }

    public List<Expense> findAllByFilter(LocalDate from, LocalDate to) {
        return expenseRepository.findAllByCreatedDateBetween(
                from.atStartOfDay(), to.atTime(23, 59, 59),
                Sort.by(Sort.Direction.DESC, "createdDate") // Сортировка по убыванию
        );
    }
}
