package com.project4.com.repository;

import com.project4.com.domain.Book;
import com.project4.com.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Category} entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {}
