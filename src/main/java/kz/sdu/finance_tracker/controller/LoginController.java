package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.dto.LoginDto;
import kz.sdu.finance_tracker.dto.UserRegistrationDto;
import kz.sdu.finance_tracker.dto.UserResetDto;
import kz.sdu.finance_tracker.dto.UserResetPasswordDto;
import kz.sdu.finance_tracker.entity.UserResetPassword;
import kz.sdu.finance_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/open-api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationDto> register(@RequestBody UserRegistrationDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/reset-password")
    private ResponseEntity<HttpStatus> resetPassword(@RequestBody UserResetDto userResetDto) {
        userService.resetPassword(userResetDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        return ResponseEntity.ok(userService.validateToken(token));
    }

    @PostMapping("/reset-user-password")
    public ResponseEntity<HttpStatus> resetUserPassword(@RequestBody UserResetPasswordDto userResetPasswordDto) {
        userService.resetPassword(userResetPasswordDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
