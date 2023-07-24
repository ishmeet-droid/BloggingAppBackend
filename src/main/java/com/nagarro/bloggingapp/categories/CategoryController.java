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

import com.nagarro.bloggingapp.categories.dtos.CategoryRequestDto;
import com.nagarro.bloggingapp.categories.dtos.CategoryResponseDto;
import com.nagarro.bloggingapp.common.ApiResponse;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryServiceImpl categoryService;

    CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<CategoryResponseDto> createCategory(
            @RequestBody CategoryRequestDto createCategory) {
        CategoryResponseDto responseCategory = categoryService
                .createCategory(createCategory);
        return new ResponseEntity<>(responseCategory, HttpStatus.CREATED);
    }
     @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> responseCategories = categoryService
                .getAllCategories();
        return new ResponseEntity<>(responseCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Long id) {
        CategoryResponseDto responseCategory = categoryService.getCategory(id);
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
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto updateCategory) {
        CategoryResponseDto responseCategory = categoryService
                .updateCategory(updateCategory, id);
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategoryByName(
            @PathVariable Long id,
            @RequestBody HashMap<String,String> updateCategoryName) {
        CategoryResponseDto responseCategory = categoryService
                .updateCategoryName(updateCategoryName, id);
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }

    @PatchMapping("/update-description/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategoryByDescription(
            @PathVariable Long id,
            @RequestBody HashMap<String,String>  updateCategoryDescription) {
        CategoryResponseDto responseCategory = categoryService
                .updateCategoryDescription(updateCategoryDescription, id);
        return new ResponseEntity<>(responseCategory, HttpStatus.OK);
    }
}
