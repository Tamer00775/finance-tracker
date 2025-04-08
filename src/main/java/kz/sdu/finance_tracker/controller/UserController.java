package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.dto.UserDto;
import kz.sdu.finance_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> currentUser() {
        return ResponseEntity.ok(userService.currentUser());
    }
}
