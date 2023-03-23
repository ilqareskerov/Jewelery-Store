package com.company.jewelrystore.dto;

import com.company.jewelrystore.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoConvertor {
    public ProductDto productDtoConvert(Product product){
        return new ProductDto(product.getId(),
                product.getProduct_name(),
                product.getProduct_desc(),
                product.getProduct_price(),
                product.getProduct_count(),
                product.getProduct_discount(),
                product.getCategory());
    }
    public List<ProductDto> productDtoListConvertor(List<Product> productList){
        return productList.stream().map(this::productDtoConvert).collect(Collectors.toList());
    }
}
