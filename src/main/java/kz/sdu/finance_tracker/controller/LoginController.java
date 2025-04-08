package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.dto.LoginDto;
import kz.sdu.finance_tracker.dto.UserRegistrationDto;
import kz.sdu.finance_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ResponseEntity<UserRegistrationDto> resetPassword(@RequestBody UserRegistrationDto userRegistrationDto) {
        return ResponseEntity.ok(userService.register(userRegistrationDto));
    }
}
