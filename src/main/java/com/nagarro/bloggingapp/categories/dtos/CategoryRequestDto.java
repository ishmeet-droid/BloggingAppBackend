package com.nagarro.bloggingapp.categories.dtos;

import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequestDto {

    @NotEmpty(message = "Category name cannot be empty")
    private String categoryName;

    @NotEmpty(message = "Category description cannot be null")
    @Size(min = 10, max = 1000, message = "Category description should be between 10 to 1000 characters")
    private String categoryDescription;


}
