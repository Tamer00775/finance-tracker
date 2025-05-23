package kz.sdu.finance_tracker.service;

import kz.sdu.finance_tracker.dto.*;
import kz.sdu.finance_tracker.entity.User;
import kz.sdu.finance_tracker.entity.UserResetPassword;
import kz.sdu.finance_tracker.enums.OperationType;
import kz.sdu.finance_tracker.mapper.UserMapper;
import kz.sdu.finance_tracker.repository.UserRepository;
import kz.sdu.finance_tracker.repository.UserResetPasswordRepository;
import kz.sdu.finance_tracker.security.JwtUtil;
import kz.sdu.finance_tracker.security.PersonDetailsService;
import kz.sdu.finance_tracker.utils.SecurityUtils;
import liquibase.pro.packaged.R;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PersonDetailsService personDetailsService;
    private final JwtUtil jwtUtil;
    private final ExpenseService expenseService;
    private final EmailService emailService;
    private final UserResetPasswordRepository userResetPasswordRepository;

    @Transactional
    public UserRegistrationDto register(UserRegistrationDto userRegistrationDto) {
        User user = userMapper.toEntity(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto currentUser() {
        User currentUser = SecurityUtils.getCurrentUser();
        UserDto userDto = userMapper.toUserDto(currentUser);
        userDto.setTotalIncome(expenseService.getSum(currentUser, OperationType.DEPOSIT));
        userDto.setTotalOutcome(expenseService.getSum(currentUser, OperationType.WITHDRAW));

        return userDto;
    }

    public Map<String, String> login(LoginDto loginDto) {
        Map<String, String> map = new HashMap<>();
        UserDetails userDetails = personDetailsService.loadUserByUsername(loginDto.getLogin());
        if (!passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {
            throw new IllegalArgumentException("Password was incorrect");
        }
        map.put("token", jwtUtil.generateToken(loginDto.getLogin()));

        return map;
    }

    public void updatePassword(UserRegistrationDto userRegistrationDto) {
        Optional<User> byEmail = userRepository.findByEmail(userRegistrationDto.getEmail());
        if (byEmail.isEmpty()) {
            throw new IllegalArgumentException("User is not present!");
        }
        User user = byEmail.get();
        user.setPassword(jwtUtil.generateToken(userRegistrationDto.getPassword()));
        userRepository.save(user);
    }

    public void resetPassword(UserResetDto userResetDto) {
        String email = userResetDto.getEmail();
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new IllegalArgumentException("User is not present!");
        }

        User user = byEmail.get();
        Random random = new Random();
        int sixDigit = 100000 + random.nextInt(900000);
        UserResetPassword userResetPassword = new UserResetPassword();
        userResetPassword.setResetted(false);
        userResetPassword.setUser(user);
        userResetPassword.setTemporaryPassword(String.valueOf(sixDigit));

        emailService.sendSimpleMessage(user.getEmail(), sixDigit);

        userResetPasswordRepository.save(userResetPassword);
    }

    public boolean validateToken(String token) {
        Optional<UserResetPassword> byTemporaryPasswordAndResettedFalse =
                userResetPasswordRepository.findByTemporaryPasswordAndResettedFalse(token);
        return byTemporaryPasswordAndResettedFalse.isPresent();
    }

    public void resetPassword(UserResetPasswordDto dto) {
        Optional<UserResetPassword> byTemporaryPasswordAndResettedFalse =
                userResetPasswordRepository.findByTemporaryPasswordAndResettedFalse(dto.getToken());
        if (byTemporaryPasswordAndResettedFalse.isEmpty()) {
            throw new IllegalArgumentException("Token is not present!");
        }
        UserResetPassword userResetPassword = byTemporaryPasswordAndResettedFalse.get();
        User user = userResetPassword.getUser();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
