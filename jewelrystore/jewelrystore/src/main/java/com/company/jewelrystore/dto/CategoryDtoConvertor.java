package com.company.jewelrystore.dto;

import com.company.jewelrystore.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConvertor {
    public CategoryDto categoryDtoConvertor(Category category){
        CategoryDto categoryDto = new CategoryDto(category.getId(),category.getCategory_name());
        return categoryDto;
    }
    public List<CategoryDto> categoryDtoListConvertor(List<Category> category){

        return category.stream().map(this::categoryDtoConvertor).collect(Collectors.toList());
    }
}
