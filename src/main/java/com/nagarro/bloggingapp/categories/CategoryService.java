package com.nagarro.bloggingapp.categories;

import java.util.HashMap;
import java.util.List;

import com.nagarro.bloggingapp.categories.dtos.CategoryRequestDto;
import com.nagarro.bloggingapp.categories.dtos.CategoryResponseDto;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto createCategory);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategory(Long id);

    CategoryResponseDto updateCategory(CategoryRequestDto updateCategory, Long id);

    CategoryResponseDto getCategoryByName(String name);

    CategoryResponseDto updateCategoryName(HashMap<String,String> map, Long id);

    CategoryResponseDto updateCategoryDescription(HashMap<String,String> map, Long id);

    void deleteCategory(Long id);
    
}
