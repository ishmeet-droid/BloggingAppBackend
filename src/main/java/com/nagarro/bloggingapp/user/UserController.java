package com.nagarro.bloggingapp.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bloggingapp.common.ApiResponse;
import com.nagarro.bloggingapp.user.dtos.UserRequestDto;
import com.nagarro.bloggingapp.user.dtos.UpdateAbtAndPassDto;
import com.nagarro.bloggingapp.user.dtos.UserResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userServicesImpl;

    UserController(UserServiceImpl userServicesImpl) {
        this.userServicesImpl = userServicesImpl;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<List<UserResponseDto>>(
                userServicesImpl.getUsers(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<UserResponseDto>(
                userServicesImpl.getUserById(id),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody UserRequestDto createUser) {

        UserResponseDto responseUser = userServicesImpl.createUser(createUser);

        return new ResponseEntity<UserResponseDto>(
                responseUser,
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @Valid @RequestBody UserRequestDto updateUser, @PathVariable Long id) {
        return new ResponseEntity<UserResponseDto>(
                userServicesImpl.updateUser(updateUser, id),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserPassAndAbout(
            @Valid @RequestBody UpdateAbtAndPassDto updateUserPassAndAbout,
            @PathVariable Long id) {
        return new ResponseEntity<UserResponseDto>(
                userServicesImpl.updateUserPassAndAbout(updateUserPassAndAbout, id),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {

        userServicesImpl.deleteUser(id);

        return new ResponseEntity<ApiResponse>(
                new ApiResponse(
                        String.format("User deleted with UserId : %s", id), true),
                HttpStatus.ACCEPTED);
    }
}
