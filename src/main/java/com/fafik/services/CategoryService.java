package com.fafik.services;

import com.fafik.api.v1.model.CategoryDTO;
import com.fafik.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<CategoryDTO>  getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
