package com.nagarro.bloggingapp.comments.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDto {
    
    //@NotEmpty(message = "Comment cannot be empty")
    private String comment;

}
