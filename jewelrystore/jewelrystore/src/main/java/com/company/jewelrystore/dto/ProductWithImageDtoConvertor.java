package com.company.jewelrystore.dto;

import com.company.jewelrystore.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductWithImageDtoConvertor {
    private final ImageDtoConvertor imageDtoConvertor;
    private final CategoryDtoConvertor categoryDtoConvertor;

    public ProductWithImageDtoConvertor(ImageDtoConvertor imageDtoConvertor, CategoryDtoConvertor categoryDtoConvertor) {
        this.imageDtoConvertor = imageDtoConvertor;
        this.categoryDtoConvertor = categoryDtoConvertor;
    }

    public ProductWithImageDto productWithImageDtoConvertor(Product product){
        List<ImageDto> imageDtoList=imageDtoConvertor.imageDtoListConvert(product.getImages());
        CategoryDto categoryDto= categoryDtoConvertor.categoryDtoConvertor(product.getCategory());
        ProductWithImageDto product1 =new ProductWithImageDto(product.getId(),
                product.getProduct_name(),
                product.getProduct_desc(),
                product.getProduct_price(),
                product.getProduct_count(),
                product.getProduct_discount(),
                imageDtoList,
                categoryDto);
        return product1;
    }
    public List<ProductWithImageDto> productListWithImageDtoConvertor(List<Product> productList){
        return productList.stream().map(this::productWithImageDtoConvertor).collect(Collectors.toList());
    }

}
