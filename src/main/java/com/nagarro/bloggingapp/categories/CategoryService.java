package com.nagarro.bloggingapp.categories;

import java.util.HashMap;
import java.util.List;

import com.nagarro.bloggingapp.categories.dtos.CreateCategory;
import com.nagarro.bloggingapp.categories.dtos.CategoryResponse;

public interface CategoryService {

    CategoryResponse createCategory(CreateCategory createCategory);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategory(Long id);

    CategoryResponse updateCategory(CreateCategory updateCategory, Long id);

    CategoryResponse getCategoryByName(String name);

    CategoryResponse updateCategoryName(HashMap<String,String> map, Long id);

    CategoryResponse updateCategoryDescription(HashMap<String,String> map, Long id);

    void deleteCategory(Long id);
    
}
