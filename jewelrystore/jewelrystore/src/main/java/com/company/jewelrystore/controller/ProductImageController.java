package com.company.jewelrystore.controller;

import com.company.jewelrystore.dto.ProductWithImageDto;
import com.company.jewelrystore.dto.ResponseMessage;
import com.company.jewelrystore.model.Image;
import com.company.jewelrystore.service.ImageService;
import com.company.jewelrystore.service.ProductImageService;
import com.company.jewelrystore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products/{productId}/images")
public class ProductImageController {
    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping
    public ResponseEntity<ProductWithImageDto> addImage(@PathVariable("productId") String productId, @RequestParam("image") List<MultipartFile> image) {
        return ResponseEntity.ok(productImageService.addImage(productId, image));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteProductImage(@PathVariable("productId") String productId,String imageId){
        productImageService.deleteProductImage(productId,imageId);
        return ResponseEntity.ok().build();
    }
}

