package com.nagarro.bloggingapp.Users.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    
    private Long id;

    private String name;
    
    private String email;
    
    private String about;
}
