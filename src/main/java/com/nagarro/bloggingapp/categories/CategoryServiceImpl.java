package com.nagarro.bloggingapp.categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nagarro.bloggingapp.categories.dtos.CategoryRequestDto;
import com.nagarro.bloggingapp.categories.dtos.CategoryResponseDto;
import com.nagarro.bloggingapp.common.ResourceNotFound;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper categoryMapper;

    CategoryServiceImpl(CategoryRepository categoryRepository,
            ModelMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto createCategory) {

        CategoryEntity categoryEntity = categoryMapper.map(
                createCategory, CategoryEntity.class);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponseDto responseCategory = categoryMapper.map(savedCategory,
                CategoryResponseDto.class);
        return responseCategory;
    }

    @Override
    public void deleteCategory(Long id) {

        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Delete-Category", "CategoryId", id));
        categoryRepository.delete(categoryEntity);

    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryEntity> categoryEntity = categoryRepository.findAll();

        List<CategoryResponseDto> responseCategory = new ArrayList<>();

        for (CategoryEntity category : categoryEntity) {
            responseCategory.add(categoryMapper.map(category, CategoryResponseDto.class));
        }
        return responseCategory;
    }

    @Override
    public CategoryResponseDto getCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Get-Category-By-Id", "CategoryId", id));
        CategoryResponseDto responseCategory = categoryMapper.map(categoryEntity,
                CategoryResponseDto.class);
        return responseCategory;
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto updateCategory, Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-Category",
                        "CategoryId", id));
        categoryMapper.map(updateCategory, categoryEntity);
        // categoryEntity.setCategoryName(updateCategory.getCategoryName());
        // categoryEntity.setCategoryDescription(updateCategory.getCategoryDescription());
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponseDto responseCategory = categoryMapper.map(savedCategory,
                CategoryResponseDto.class);
        return responseCategory;
    }

    @Override
    public CategoryResponseDto getCategoryByName(String name) {
        //One Category Id should be associated with one category name
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(name);
        CategoryResponseDto responseCategory = categoryMapper.map(categoryEntity,
                CategoryResponseDto.class);
        return responseCategory;
    }

    @Override
    public CategoryResponseDto updateCategoryDescription(HashMap<String,String> map, Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-Category-Description",
                        "CategoryId", id));
        categoryEntity.setCategoryDescription(map.get("categoryDescription"));
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponseDto responseCategory = categoryMapper.map(savedCategory,
                CategoryResponseDto.class);
        return responseCategory;
    }

    @Override
    public CategoryResponseDto updateCategoryName(HashMap<String,String> map, Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-Category-Name",
                        "CategoryId", id));
        categoryEntity.setCategoryName(map.get("categoryName"));
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponseDto responseCategory = categoryMapper.map(savedCategory,
                CategoryResponseDto.class);
        return responseCategory;
    }

    

}
