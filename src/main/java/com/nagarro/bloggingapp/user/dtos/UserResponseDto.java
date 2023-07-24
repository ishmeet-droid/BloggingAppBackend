package com.nagarro.bloggingapp.user.dtos;

import java.util.HashSet;
import java.util.Set;

import com.nagarro.bloggingapp.role.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    
    private Long id;

    private String name;
    
    private String email;
    
    private String about;

    private Set<RoleEntity> roles = new HashSet<>();

}
