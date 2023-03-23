package com.company.jewelrystore.controller;

import com.company.jewelrystore.dto.CategoryDto;
import com.company.jewelrystore.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestParam(required = false,name = "category_name") String  category_name){
        return ResponseEntity.ok(categoryService.createCategory(category_name));
    }
    @PutMapping("/update")
    public ResponseEntity<CategoryDto> updateCategory(@RequestParam(required = false,name = "id") String id,@RequestParam(required = false,name = "category_name") String  category_name){
        return ResponseEntity.ok(categoryService.updateCategoryName(id,category_name));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategory(@RequestParam(required = false,name = "id") String id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();

    }
}
