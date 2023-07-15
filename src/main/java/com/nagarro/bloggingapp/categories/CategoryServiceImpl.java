package com.nagarro.bloggingapp.categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nagarro.bloggingapp.categories.dtos.CreateCategory;
import com.nagarro.bloggingapp.categories.dtos.CategoryResponse;
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
    public CategoryResponse createCategory(CreateCategory createCategory) {

        CategoryEntity categoryEntity = categoryMapper.map(
                createCategory, CategoryEntity.class);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponse responseCategory = categoryMapper.map(savedCategory,
                CategoryResponse.class);
        return responseCategory;
    }

    @Override
    public void deleteCategory(Long id) {

        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Delete-Category", "CategoryId", id));
        categoryRepository.delete(categoryEntity);

    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryEntity> categoryEntity = categoryRepository.findAll();

        List<CategoryResponse> responseCategory = new ArrayList<>();

        for (CategoryEntity category : categoryEntity) {
            responseCategory.add(categoryMapper.map(category, CategoryResponse.class));
        }
        return responseCategory;
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Get-Category-By-Id", "CategoryId", id));
        CategoryResponse responseCategory = categoryMapper.map(categoryEntity,
                CategoryResponse.class);
        return responseCategory;
    }

    @Override
    public CategoryResponse updateCategory(CreateCategory updateCategory, Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-Category",
                        "CategoryId", id));
        categoryMapper.map(updateCategory, categoryEntity);
        // categoryEntity.setCategoryName(updateCategory.getCategoryName());
        // categoryEntity.setCategoryDescription(updateCategory.getCategoryDescription());
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponse responseCategory = categoryMapper.map(savedCategory,
                CategoryResponse.class);
        return responseCategory;
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        //One Category Id should be associated with one category name
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(name);
        CategoryResponse responseCategory = categoryMapper.map(categoryEntity,
                CategoryResponse.class);
        return responseCategory;
    }

    @Override
    public CategoryResponse updateCategoryDescription(HashMap<String,String> map, Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-Category-Description",
                        "CategoryId", id));
        categoryEntity.setCategoryDescription(map.get("categoryDescription"));
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponse responseCategory = categoryMapper.map(savedCategory,
                CategoryResponse.class);
        return responseCategory;
    }

    @Override
    public CategoryResponse updateCategoryName(HashMap<String,String> map, Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Update-Category-Name",
                        "CategoryId", id));
        categoryEntity.setCategoryName(map.get("categoryName"));
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryResponse responseCategory = categoryMapper.map(savedCategory,
                CategoryResponse.class);
        return responseCategory;
    }

    

}
