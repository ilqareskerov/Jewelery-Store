package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.*;
import com.company.jewelrystore.exception.GenericException;
import com.company.jewelrystore.model.Category;
import com.company.jewelrystore.model.Product;
import com.company.jewelrystore.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductDtoConvertor productDtoConvertor;
    private final ProductWithImageDtoConvertor productWithImageDtoConvertor;


    public ProductService(ProductRepository productRepository, CategoryService categoryService, ProductDtoConvertor productDtoConvertor, ProductWithImageDtoConvertor productWithImageDtoConvertor) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productDtoConvertor = productDtoConvertor;
        this.productWithImageDtoConvertor = productWithImageDtoConvertor;
    }

    public Product findProductById(String id){
        return productRepository.findById(id).orElseThrow(()-> new GenericException(HttpStatus.NOT_FOUND, ErrorCode.PRODUCT_NOT_FOUND));
    }
    public List<ProductWithImageDto> getAllProduct(){
        return productWithImageDtoConvertor.productListWithImageDtoConvertor(productRepository.findAll());
    }
    public List<Product> getAllProductByCategoryName(String category){
        return productRepository.findAllByCategory(category);
    }
    public List<Product> getAllProductByPriceFromLessToMore(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC));
    }
    public List<Product> getAllProductByPriceFromMoreToLess(){
        return productRepository.findAll(Sort.by(Sort.Direction.DESC));
    }
    @Transactional
    public ProductDto createProduct(@NotNull CreateProductRequest createProductRequest){
        Category category= categoryService.findById(createProductRequest.getCategory_id());
        Product product = new Product(createProductRequest.getProduct_name(),
                createProductRequest.getProduct_desc(),
                createProductRequest.getProduct_price(),
                createProductRequest.getProduct_count(),
                createProductRequest.getProduct_discount(),
                category);
        return productDtoConvertor.productDtoConvert(productRepository.save(product));
    }
    @Transactional
    public ProductDto updateProduct(String id, UpdateProductRequest updateProductRequest){
        Product product=findProductById(id);
        Category category=categoryService.findById(updateProductRequest.getCategory_id());
        Product product1=new Product(product.getId(),updateProductRequest.getProduct_name(),updateProductRequest.getProduct_desc(),product.getProduct_price(),product.getProduct_count(),product.getProduct_discount(),category);
        return productDtoConvertor.productDtoConvert(productRepository.save(product1));
    }
    public void updateProductImages(Product product){
        productRepository.save(product);
    }
    @Transactional
    public void deleteProduct(String id) {
        Product product=findProductById(id);
        productRepository.deleteById(product.getId());
    }
}
