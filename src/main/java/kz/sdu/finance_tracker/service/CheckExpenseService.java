package kz.sdu.finance_tracker.service;

import kz.sdu.finance_tracker.dto.CheckDto;
import kz.sdu.finance_tracker.dto.CheckExpenseDto;
import kz.sdu.finance_tracker.entity.CheckExpense;
import kz.sdu.finance_tracker.entity.User;
import kz.sdu.finance_tracker.mapper.CheckExpenseMapper;
import kz.sdu.finance_tracker.repository.CheckExpenseRepository;
import kz.sdu.finance_tracker.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j

public class CheckExpenseService {

    private final MinioService minioService;
    private final CheckExpenseRepository checkExpenseRepository;
    private final CheckExpenseMapper checkExpenseMapper;

    @Transactional
    public void save(CheckDto checkDto) {
        CheckExpense checkExpense = new CheckExpense();
        checkExpense.setTitle(checkDto.getTitle());
        User currentUser = SecurityUtils.getCurrentUser();
        checkExpense.setCreated_by(currentUser.getEmail());
        checkExpense.setUser(currentUser);
        checkExpense.setCheckUuid(minioService.saveToMinio(checkDto.getImage()));
        checkExpense.setCreatedDate(Instant.now());

        checkExpenseRepository.save(checkExpense);
    }

    @Transactional(readOnly = true)
    public Page<CheckExpenseDto> findAll(Pageable pageable) {
        return checkExpenseRepository.findAll(pageable)
                .map(checkExpenseMapper::toDto);
    }

    public InputStream getImage(String imageId) {
        return minioService.getImage(imageId);
    }


}
