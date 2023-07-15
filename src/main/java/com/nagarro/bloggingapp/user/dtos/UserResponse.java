package com.nagarro.bloggingapp.user.dtos;

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
