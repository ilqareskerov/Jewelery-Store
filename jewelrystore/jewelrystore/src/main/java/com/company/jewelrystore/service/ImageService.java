package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.ErrorCode;
import com.company.jewelrystore.exception.GenericException;
import com.company.jewelrystore.model.Image;
import com.company.jewelrystore.repository.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductService productService;
    private final ObjectMapper objectMapper;


    public ImageService(ImageRepository imageRepository, ProductService productService, ObjectMapper objectMapper) {
        this.imageRepository = imageRepository;
        this.productService = productService;
        this.objectMapper = objectMapper;
    }
    public Image findImageById(String id){
        return objectMapper.convertValue(imageRepository.findById(id).orElseThrow(()-> new GenericException(HttpStatus.NOT_FOUND, ErrorCode.IMAGE_NOT_FOUND)),Image.class) ;
    }
    @Transactional
    public void saveImage(Image image){
        imageRepository.save(image);
    }
    public Image getFile(String id) {
        return imageRepository.findById(id).get();
    }
    @Transactional
    public void deleteImage(String id){
        Image image=findImageById(id);
        imageRepository.deleteById(image.getId());

    }
}
