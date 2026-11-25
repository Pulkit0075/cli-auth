package com.practice.auth_cli.user;

import com.practice.auth_cli.user.dto.UserResponse;
import com.practice.auth_cli.user.dto.UpdateUsernameRequest;
import com.practice.auth_cli.user.dto.UpdateEmailRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers()
            .stream()
            .map(UserResponse::from)
            .toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUserByID(@PathVariable Long id) {
        return UserResponse.from(
            userService.getUserByID(id)
        );
    }

    @GetMapping("/username/{username}")
    public UserResponse getUserByUsername(
        @PathVariable String username
    ) {
        return UserResponse.from(
            userService.getUserByUsername(username)
        );
    }

    @GetMapping("/email")
    public UserResponse getUserByEmail(
        @RequestParam String email
    ) {
        return UserResponse.from(
            userService.getUserByEmail(email)
        );
    }

    @PatchMapping("/{id}/username")
    public UserResponse updateUsername(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUsernameRequest request
    ) {
        return UserResponse.from(
            userService.updateUsername(id, request.username())
        );
    }

    @PatchMapping("/{id}/email")
    public UserResponse updateEmail(
        @PathVariable Long id,
        @Valid @RequestBody UpdateEmailRequest request
    ) {
        return UserResponse.from(
            userService.updateEmail(id, request.email())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable Long id
    ) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}