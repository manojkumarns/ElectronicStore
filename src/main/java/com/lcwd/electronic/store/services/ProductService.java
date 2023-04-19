package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    //create
    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto, String productId);

    //delete
    void delete(String productId);

    //getSingle
    ProductDto get(String productId);

    //getAll
    PageableResponse<ProductDto> geAll(int pageNumber ,int pageSize,String sortBy, String sortDir);

    //getALl : live
    PageableResponse<ProductDto> getAllLive(int pageNumber ,int pageSize,String sortBy, String sortDir);

    //searchProduct
    PageableResponse<ProductDto>  searchByTitle(String subTitle,int pageNumber ,int pageSize,String sortBy, String sortDir);

    //other method
}
