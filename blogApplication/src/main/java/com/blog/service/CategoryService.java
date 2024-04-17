package com.blog.service;

import java.util.List;

import com.blog.exceptions.CategoryNotFoundException;
import com.blog.model.Category;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId) throws CategoryNotFoundException;

    Category createCategory(Category category);

    Category updateCategory(Long categoryId, Category categoryDetails) throws CategoryNotFoundException;

    void deleteCategory(Long categoryId) throws CategoryNotFoundException;
}
