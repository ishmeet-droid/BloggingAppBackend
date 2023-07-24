package com.nagarro.bloggingapp.posts.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostRequestDto {
    
    @NotEmpty
    @Size(min = 10, max = 100)
    private String title;
    
    @NotEmpty
    @Size(min = 10, max = 1000)
    private String content;

    private String imageURI;
    
}
