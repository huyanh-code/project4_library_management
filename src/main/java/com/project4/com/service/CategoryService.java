package com.project4.com.service;

import com.project4.com.repository.AuthorRepository;
import com.project4.com.repository.CategoryRepository;
import com.project4.com.service.dto.AuthorDTO;
import com.project4.com.service.dto.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing author.
 */
@Service
@Transactional
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> getAllCategory(Pageable pageable) {
        return categoryRepository
            .findAll(pageable)
            .map(categoryEntity -> new CategoryDTO(categoryEntity.getMaLoai(), categoryEntity.getTenLoai()));
    }
}
