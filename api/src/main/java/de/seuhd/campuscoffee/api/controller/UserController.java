package de.seuhd.campuscoffee.api.controller;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.api.mapper.UserDtoMapper;
import de.seuhd.campuscoffee.domain.ports.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Operations related to user management.")
@Controller
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    //TODO: Implement user controller
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    // GET /api/users
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userDtoMapper::fromDomain)
                .toList();
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userDtoMapper.fromDomain(userService.getById(id));
    }

    // GET /api/users/filter?loginName=xxx
    @GetMapping("/filter")
    public UserDto getUserByLoginName(@RequestParam String loginName) {
        return userDtoMapper.fromDomain(userService.getByLoginName(loginName));
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        var created = userService.upsert(userDtoMapper.toDomain(userDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userDtoMapper.fromDomain(created));
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @Valid @RequestBody UserDto userDto) {
        var user = userDtoMapper.toDomain(userDto).toBuilder()
                .id(id)
                .build();
        return userDtoMapper.fromDomain(userService.upsert(user));
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
