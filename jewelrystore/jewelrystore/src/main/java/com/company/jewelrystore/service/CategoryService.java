package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.CategoryDto;
import com.company.jewelrystore.dto.CategoryDtoConvertor;
import com.company.jewelrystore.dto.ErrorCode;
import com.company.jewelrystore.exception.GenericException;
import com.company.jewelrystore.model.Category;
import com.company.jewelrystore.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConvertor categoryDtoConvertor;
    private final ObjectMapper objectMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoConvertor categoryDtoConvertor, ObjectMapper objectMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConvertor = categoryDtoConvertor;
        this.objectMapper = objectMapper;
    }
    protected Category findById(String id){
        return objectMapper.convertValue(categoryRepository.findById(id).orElseThrow(()->new GenericException(HttpStatus.NOT_FOUND, ErrorCode.CATEGORY_NOT_FOUND)),Category.class);
    }
    @Transactional
    public CategoryDto createCategory(String createCategoryRequest){
       Category category = new Category(createCategoryRequest);
       return categoryDtoConvertor.categoryDtoConvertor(categoryRepository.save(category));
    }
    @Transactional
    public CategoryDto updateCategoryName(String id, String category_name) {
        Category category=findById(id);
       Category category1=new Category(category.getId(),category_name);
       return categoryDtoConvertor.categoryDtoConvertor(categoryRepository.save(category1));
    }
    @Transactional
    public void deleteCategory(String id){
        Category category=findById(id);
        categoryRepository.deleteById(category.getId());

    }

    public List<CategoryDto> getAllCategory() {

        return categoryDtoConvertor.categoryDtoListConvertor(categoryRepository.findAll());
    }
}
