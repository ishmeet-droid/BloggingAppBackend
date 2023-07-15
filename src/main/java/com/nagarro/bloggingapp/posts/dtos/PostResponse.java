package com.nagarro.bloggingapp.posts.dtos;

import com.nagarro.bloggingapp.Users.dtos.UserResponse;
import com.nagarro.bloggingapp.categories.dtos.CategoryResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private Long id;
    
    private String title;

    private String content;

    private String date;

    private String imageURI;

    private CategoryResponse category;

    private UserResponse user;


}
