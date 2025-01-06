package com.project4.com.repository;

import com.project4.com.domain.Author;
import com.project4.com.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Author} entity.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(
        value = """
        SELECT
            ma_tac_gia,
            ten_tac_gia,
            nam_sinh,
            que_quan
        FROM tbl_tacgia
        WHERE ten_tac_gia LIKE CONCAT('%', :query, '%')
        """,
        countQuery = """
        SELECT COUNT(*)
        FROM tbl_tacgia
        WHERE ten_tac_gia LIKE CONCAT('%', :query, '%')
        """,
        nativeQuery = true
    )
    Page<Object[]> search(String query, Pageable pageable);
}
