package com.project4.com.repository;

import com.project4.com.domain.Authority;
import com.project4.com.domain.Readers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Readers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReadersRepository extends JpaRepository<Readers, Integer> {
    @Query(
        """
        SELECT
            madocgia,
            ten_doc_gia,
            gioi_tinh,
            diachi,
            sdt
        FROM tbl_docgia
        WHERE ten_doc_gia LIKE CONCAT('%', :query, '%')
        OR diachi LIKE CONCAT('%', :query, '%')
        """
    )
    Page<Object[]> search(String query, Pageable pageable);
}
