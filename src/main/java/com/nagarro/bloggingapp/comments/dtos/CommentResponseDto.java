package com.nagarro.bloggingapp.comments.dtos;


import java.util.Date;

import com.nagarro.bloggingapp.posts.dtos.PostResponseDto;
import com.nagarro.bloggingapp.user.dtos.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentResponseDto {
    
    private Long id;
    
    private String comment;

    private Date createdAt;

    // private PostResponseDto post;

    private UserResponseDto user;
    
}
