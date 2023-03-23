package com.company.jewelrystore.dto;

import com.company.jewelrystore.model.Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ImageDtoConvertor {
    public ImageDto imageDtoConvert(Image image){
        return new ImageDto(image.getId(),image.getImage_name());
    }
    public List<ImageDto> imageDtoListConvert(List<Image> images){
        return images.stream().map(this::imageDtoConvert).collect(Collectors.toList());
    }
}
