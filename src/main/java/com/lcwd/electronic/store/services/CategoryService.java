package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;

import java.util.List;

public interface CategoryService {
    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto , String categoryId);

    //delete
    void delete(String categoryId);

    //getall
    PageableResponse<CategoryDto>  getAll(int pageNumber,int pageSize, String sortBy , String sortDir);

    //getSingleCategoryDetail
    CategoryDto get(String categoryId);
    //search
    List<CategoryDto> searchCategory(String keyword);

}
