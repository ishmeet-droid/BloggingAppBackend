package com.nagarro.bloggingapp.categories;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bloggingapp.categories.dtos.CreateCategory;
import com.nagarro.bloggingapp.categories.dtos.CategoryResponse;
import com.nagarro.bloggingapp.common.ApiResponse;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryServiceImpl categoryService;

    CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody CreateCategory createCategory) {
        CategoryResponse responseCategory = categoryService
                .createCategory(createCategory);
        return new ResponseEntity<>(responseCategory, HttpStatus.CREATED);
    }
     @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> responseCategories = categoryService
                .getAllCategories();
        return new ResponseEntity<>(responseCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        CategoryResponse responseCategory = categoryService.getCategory(id);
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                String.format("User deleted with UserId : %s", id),
                true),
                HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long id,
            @RequestBody CreateCategory updateCategory) {
        CategoryResponse responseCategory = categoryService
                .updateCategory(updateCategory, id);
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryByName(
            @PathVariable Long id,
            @RequestBody HashMap<String,String> updateCategoryName) {
        CategoryResponse responseCategory = categoryService
                .updateCategoryName(updateCategoryName, id);
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }

    @PatchMapping("/update-description/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryByDescription(
            @PathVariable Long id,
            @RequestBody HashMap<String,String>  updateCategoryDescription) {
        CategoryResponse responseCategory = categoryService
                .updateCategoryDescription(updateCategoryDescription, id);
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }
}
