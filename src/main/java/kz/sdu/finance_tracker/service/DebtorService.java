package kz.sdu.finance_tracker.service;

import kz.sdu.finance_tracker.repository.DebtorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DebtorService {

    private final DebtorRepository debtorRepository;


}
