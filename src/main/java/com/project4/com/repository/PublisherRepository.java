package com.project4.com.repository;

import com.project4.com.domain.Book;
import com.project4.com.domain.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Publisher} entity.
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    @Query(
        value = """
        SELECT
            MaNXB,
            TenNXB,
            dia_chi,
            Sdt
        FROM tbl_nhaxuatban
        WHERE TenNXB LIKE CONCAT('%', :query, '%')
        OR dia_chi LIKE CONCAT('%', :query, '%')
        """,
        countQuery = """
        SELECT COUNT(*)
        FROM tbl_nhaxuatban
        WHERE TenNXB LIKE CONCAT('%', :query, '%')
        OR dia_chi LIKE CONCAT('%', :query, '%')
        """,
        nativeQuery = true
    )
    Page<Object[]> search(String query, Pageable pageable);
}
