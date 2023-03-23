package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.ProductWithImageDto;
import com.company.jewelrystore.dto.ProductWithImageDtoConvertor;
import com.company.jewelrystore.model.Image;
import com.company.jewelrystore.model.Product;
import com.company.jewelrystore.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductImageService {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductWithImageDtoConvertor productWithImageDtoConvertor;
    private final ImageService imageService;

    public ProductImageService(ProductRepository productRepository, ProductService productService, ProductWithImageDtoConvertor productWithImageDtoConvertor, ImageService imageService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productWithImageDtoConvertor = productWithImageDtoConvertor;
        this.imageService = imageService;
    }

    public ProductWithImageDto addImage(String productId, List<MultipartFile> images) {
        Product product = productService.findProductById(productId);
        try {
            for(MultipartFile image:images) {
                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                File directory = new File("C:\\Users\\Ilqar\\Downloads\\jewelrystore\\jewelrystore\\src\\main\\resources\\static\\images");
                if (!directory.exists()) {
                    directory.mkdir();
                }
                File imageFile = new File(directory, fileName);
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(image.getBytes());
                fos.close();
                Image newImage = new Image(fileName, image.getContentType(),product);
                imageService.saveImage(newImage);
                //product.getImages().add(newImage);
            }
            //productRepository.save(product);
            return productWithImageDtoConvertor.productWithImageDtoConvertor(productRepository.save(product));
        } catch (IOException e) {
            return null;
        }
    }
    @Transactional
    public void deleteProductImage(String productId,String id){
        Product product=productService.findProductById(productId);
        imageService.deleteImage(id);



    }
}