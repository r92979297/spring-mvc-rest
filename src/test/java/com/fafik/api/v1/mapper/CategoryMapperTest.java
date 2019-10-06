package com.fafik.api.v1.mapper;

import com.fafik.api.v1.model.CategoryDTO;
import com.fafik.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    public CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    @Test
    public void categoryToCategoryDTO() {
        Category category = new Category();
        category.setName("Fruit");
        category.setId(1L);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(1L),categoryDTO.getId());
        assertEquals("Fruit",categoryDTO.getName());
    }
}