package com.project4.com.repository;

import com.project4.com.domain.Category;
import com.project4.com.domain.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Shelf} entity.
 */
@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Integer> {}
