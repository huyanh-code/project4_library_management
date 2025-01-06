package com.project4.com.repository;

import com.project4.com.domain.Book;
import com.project4.com.service.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Book} entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(
        """
        SELECT
              s.maSoSach,
              s.tensach,
              s.matacgia,
              t.TenTacGia,
              s.maNXB,
              n.TenNXB,
              s.maloai,
              l.TenLoai,
              s.namxb,
              s.soluong,
              s.hinhanh,
              s.ghichu,
              s.maKe,
              s.trangThai
        FROM
              Book s
          JOIN
              Category l ON s.maloai = l.MaLoai
          JOIN
              Publisher n ON s.maNXB = n.MaNXB
          JOIN
              Author t ON s.matacgia = t.MaTacGia
          JOIN
              Shelf k ON s.maKe = k.MaKeSach
          WHERE
              s.tensach LIKE CONCAT('%', :query, '%')
        """
    )
    Page<Object[]> search(@Param("query") String query, Pageable pageable);
}
