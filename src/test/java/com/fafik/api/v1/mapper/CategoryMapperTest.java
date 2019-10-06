package com.fafik.api.v1.mapper;

import com.fafik.api.v1.model.CategoryDTO;
import com.fafik.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    private static final String FRUIT = "Fruit";
    private static final long ID = 1L;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    @Test
    public void categoryToCategoryDTO() {
        Category category = new Category();
        category.setName(FRUIT);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(ID),categoryDTO.getId());
        assertEquals(FRUIT,categoryDTO.getName());
    }
}