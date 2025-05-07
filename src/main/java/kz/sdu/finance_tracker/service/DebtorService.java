package kz.sdu.finance_tracker.service;

import kz.sdu.finance_tracker.dto.DebtorDto;
import kz.sdu.finance_tracker.entity.Debtor;
import kz.sdu.finance_tracker.entity.User;
import kz.sdu.finance_tracker.mapper.DebtorMapper;
import kz.sdu.finance_tracker.repository.DebtorRepository;
import kz.sdu.finance_tracker.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebtorService {

    private final UserService userService;
    private final DebtorRepository debtorRepository;
    private final DebtorMapper debtorMapper;

    @Transactional
    public DebtorDto create(DebtorDto debtorDto) {
        Debtor debtor = debtorMapper.toEntity(debtorDto);
        User currentUser = SecurityUtils.getCurrentUser();
        debtor.setUser(currentUser);

        return debtorMapper.toDto(debtorRepository.save(debtor));
    }

    @Transactional
    public DebtorDto update(Long id, DebtorDto debtorDto) {
        Optional<Debtor> byId = debtorRepository.findById(id);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }

        Debtor debtor = byId.get();
        debtorMapper.updateEntityFromDto(debtorDto, debtor);

        return debtorMapper.toDto(debtorRepository.save(debtor));
    }

    @Transactional(readOnly = true)
    public Page<DebtorDto> findAll(Pageable pageable) {
        User currentUser = SecurityUtils.getCurrentUser();
        return debtorRepository.findAllByUserAndPayedFalse(currentUser, pageable)
                .map(debtorMapper::toDto);
    }

    @Transactional
    public void payedTrue(Long id) {
        Optional<Debtor> byId = debtorRepository.findById(id);
        if (byId.isPresent()) {
            Debtor debtor = byId.get();
            debtor.setPayed(true);
            User user = debtor.getUser();
            user.setSum(debtor.getSum());
            userService.save(user);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        debtorRepository.deleteById(id);
    }

}
