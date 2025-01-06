package com.project4.com.service;

import com.project4.com.domain.*;
import com.project4.com.repository.BookRepository;
import com.project4.com.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing Books.
 */
@Service
@Transactional
public class BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookEntity -> new BookDTO());
    }

    @Transactional(readOnly = true)
    public Page<BookDTO> search(String query, Pageable pageable) {
        Page<Object[]> rawResults = bookRepository.search(query, pageable);
        // Convert Object[] to BookDTO
        Page<BookDTO> bookDTOPage = rawResults.map(objects ->
            new BookDTO(
                (Integer) objects[0], // maSach
                (String) objects[1], // tenSach
                (Integer) objects[2], // ma tac gia
                (String) objects[3], // ten tac gia
                (Integer) objects[4], // ma nha XuatBan
                (String) objects[5], // ten nha XuatBan
                (Integer) objects[6], // ma loai
                (String) objects[7], // ten loai
                (Integer) objects[8], // nam xb
                (Integer) objects[9], // so luong
                (String) objects[10], // hinh anh
                (String) objects[11], // ghi chu
                (Integer) objects[12] // ma ke
            )
        );

        return bookDTOPage;
    }
}
