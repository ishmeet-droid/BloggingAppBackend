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
import com.nagarro.bloggingapp.user.dtos.CreateUser;
import com.nagarro.bloggingapp.user.dtos.UpdateAbtAndPass;
import com.nagarro.bloggingapp.user.dtos.UserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userServicesImpl;

    UserController(UserServiceImpl userServicesImpl) {
        this.userServicesImpl = userServicesImpl;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<List<UserResponse>>(
                userServicesImpl.getUsers(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return new ResponseEntity<UserResponse>(
                userServicesImpl.getUserById(id),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUser createUser) {

        UserResponse responseUser = userServicesImpl.createUser(createUser);

        return new ResponseEntity<UserResponse>(
                responseUser,
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @Valid @RequestBody CreateUser updateUser, @PathVariable Long id) {
        return new ResponseEntity<UserResponse>(
                userServicesImpl.updateUser(updateUser, id),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserPassAndAbout(
            @Valid @RequestBody UpdateAbtAndPass updateUserPassAndAbout,
            @PathVariable Long id) {
        return new ResponseEntity<UserResponse>(
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
