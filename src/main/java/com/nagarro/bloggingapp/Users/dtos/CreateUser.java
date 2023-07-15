package com.nagarro.bloggingapp.Users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUser {
    /*
     * 
     * Not Empty checks for null and empty string
     * Not Null checks for null
     */

    @NotEmpty(message = "Name cannot be null")
    private String name;
    
    @NotEmpty(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password cannot be null")
    @Size(min = 8, max = 20, message = "Password should be between 8 to 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password should contain atleast one uppercase, one lowercase, one digit and one special character")
    private String password;


    @NotNull(message = "About cannot be null")
    @Size(max = 1000, message = "About cannot be more than 100 characters")
    private String about;

}
