package com.fafik.services;

import com.fafik.api.v1.mapper.CategoryMapper;
import com.fafik.api.v1.model.CategoryDTO;
import com.fafik.domain.Category;
import com.fafik.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private static final Long ID = 2L;
    private static final String NAME = "FRUIT";
    private CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;


    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = Arrays.asList(new Category(),new Category(),new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        assertEquals(3,categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(ID,categoryDTO.getId());
    }
}