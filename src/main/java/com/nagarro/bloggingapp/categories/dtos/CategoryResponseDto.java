package com.nagarro.bloggingapp.categories.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDto {
    
    private Long id;
    
    private String categoryName;

    private String categoryDescription;
}
