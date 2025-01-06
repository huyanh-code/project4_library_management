package com.project4.com.repository;

import com.project4.com.domain.Author;
import com.project4.com.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Author} entity.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {}
