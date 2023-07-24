package com.nagarro.bloggingapp.posts.dtos;


import java.util.List;

import com.nagarro.bloggingapp.categories.dtos.CategoryResponseDto;
import com.nagarro.bloggingapp.comments.dtos.CommentResponseDto;
import com.nagarro.bloggingapp.user.dtos.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponseDto {

    private Long id;
    
    private String title;

    private String content;

    private String date;

    private String imageURI;

    private List<CommentResponseDto> comment;

    private CategoryResponseDto category;

    private UserResponseDto user;


}
