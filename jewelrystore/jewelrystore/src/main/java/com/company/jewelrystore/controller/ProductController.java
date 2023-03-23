package com.company.jewelrystore.controller;

import com.company.jewelrystore.dto.CreateProductRequest;
import com.company.jewelrystore.dto.ProductDto;
import com.company.jewelrystore.dto.ProductWithImageDto;
import com.company.jewelrystore.dto.UpdateProductRequest;
import com.company.jewelrystore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("allproduct")
    public ResponseEntity<List<ProductWithImageDto>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());

    }
    @PostMapping("create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.ok(productService.createProduct(createProductRequest));
    }
    @PutMapping("update")
    public ResponseEntity<ProductDto> updateProduct(String id, @RequestBody UpdateProductRequest updateProductRequest){
        return ResponseEntity.ok(productService.updateProduct(id,updateProductRequest));
    }
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteProduct(String id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
