package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.*;
import com.company.jewelrystore.exception.GenericException;
import com.company.jewelrystore.model.Category;
import com.company.jewelrystore.model.Product;
import com.company.jewelrystore.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private  ProductRepository productRepository;
    private  CategoryService categoryService;
    private  ProductDtoConvertor productDtoConvertor;
    private  ProductWithImageDtoConvertor productWithImageDtoConvertor;
    private  ProductService productService;

    private ObjectMapper objectMapper=new ObjectMapper();

    @BeforeEach
    void setup(){
        productRepository= Mockito.mock(ProductRepository.class);
        categoryService=Mockito.mock(CategoryService.class);
        productDtoConvertor=Mockito.mock(ProductDtoConvertor.class);
        productWithImageDtoConvertor=Mockito.mock(ProductWithImageDtoConvertor.class);
        productService=new ProductService(productRepository,categoryService,productDtoConvertor,productWithImageDtoConvertor);
    }

    @Test
    void shouldReturnProduct_whenProductIdIsExist(){
        String id="id";
        Category category=new Category("id","string");
        Product product=new Product(id,"string", 10F,10F,10F,category );
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Product expectedResult=productService.findProductById(id);
        assertEquals(expectedResult,product);
        Mockito.verify(productRepository).findById(Mockito.anyString());
    }

    @Test
    void shouldThrowException_whenProductIdNotExist(){
        String id="id";
        Mockito.when(productRepository.findById(id)).thenThrow(new GenericException(HttpStatus.NOT_FOUND, ErrorCode.PRODUCT_NOT_FOUND));
        assertThrows( GenericException.class,
                ()->productService.findProductById(id));
        Mockito.verify(productRepository).findById(Mockito.anyString());
        Mockito.verifyNoInteractions(productDtoConvertor);
        Mockito.verifyNoInteractions(productWithImageDtoConvertor);
    }

    @Test
    void shouldReturnProductWithImageDtoList_whenProductListExist(){
        Category category=new Category("id","string");
        Product product =new Product("id","string","string",10F,10F,10F,category);
        Product product1 =new Product("id1","string","string",10F,10F,10F,category);
        Product product2 =new Product("id2","string","string",10F,10F,10F,category);
        List<Product> productList= Arrays.asList(product,product1,product2);
        CategoryDto categoryDto =new CategoryDto("id","string");
        ProductWithImageDto productWithImageDto =new ProductWithImageDto("id1","string","string",10F,10F,10F,Arrays.asList(new ImageDto("id","string")),categoryDto);
        ProductWithImageDto productWithImageDto1 =new ProductWithImageDto("id1","string","string",10F,10F,10F,Arrays.asList(new ImageDto("id","string")),categoryDto);
        ProductWithImageDto productWithImageDto2 =new ProductWithImageDto("id2","string","string",10F,10F,10F,Arrays.asList(new ImageDto("id","string")),categoryDto);
        List<ProductWithImageDto> result=Arrays.asList(productWithImageDto,productWithImageDto1,productWithImageDto2);
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        Mockito.when(productWithImageDtoConvertor.productListWithImageDtoConvertor(productList)).thenReturn(result);
        List<ProductWithImageDto> expectedResult=productService.getAllProduct();
        assertEquals(expectedResult,result);
        Mockito.verify(productRepository).findAll();
        Mockito.verify(productWithImageDtoConvertor).productListWithImageDtoConvertor(Mockito.anyList());
    }

    @Test
    void shouldReturnException_whenProductListIsNull(){
        Mockito.when(productRepository.findAll()).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class,
                ()->productService.getAllProduct());
        Mockito.verify(productRepository).findAll();
    }
    @Test
    void shouldReturnProductDto_whenSaveProduct(){
        CreateProductRequest createProductRequest =new CreateProductRequest("string","string",10F,10F,10F,"id");
        Category category=new Category("id","string");
        Product product =new Product("string","string",10F,10F,10F,category);
        Product product1 =new Product("id","string","string",10F,10F,10F,category);
        ProductDto result=new ProductDto("id","string","string",10F,10F,10F,category);
        Mockito.when(categoryService.findById(createProductRequest.getCategory_id())).thenReturn(category);
        Mockito.when(productRepository.save(product)).thenReturn(product1);
        Mockito.when(productDtoConvertor.productDtoConvert(product1)).thenReturn(result);
        ProductDto expectedResult=productService.createProduct(createProductRequest);
        assertEquals(expectedResult,result);
        Mockito.verify(categoryService).findById(Mockito.anyString());
        Mockito.verify(productRepository).save(Mockito.any());
        Mockito.verify(productDtoConvertor).productDtoConvert(Mockito.any());

    }

    @Test
    void shouldReturnException_whenCategoryNotExist(){
        CreateProductRequest createProductRequest =new CreateProductRequest("string","string",10F,10F,10F,"id");
        Mockito.when(categoryService.findById(createProductRequest.getCategory_id())).thenThrow(new GenericException(HttpStatus.NOT_FOUND, ErrorCode.CATEGORY_NOT_FOUND));
        assertThrows(GenericException.class,
                ()->productService.createProduct(createProductRequest));
        Mockito.verify(categoryService).findById(Mockito.anyString());
    }
}