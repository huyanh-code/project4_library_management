package com.project4.com.service;

import com.project4.com.repository.AuthorRepository;
import com.project4.com.repository.BookRepository;
import com.project4.com.service.dto.AuthorDTO;
import com.project4.com.service.dto.BookDTO;
import com.project4.com.service.dto.ReadersDTO;
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
public class AuthorService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        return authorRepository
            .findAll(pageable)
            .map(authorEntity ->
                new AuthorDTO(authorEntity.getMaTacGia(), authorEntity.getTenTacGia(), authorEntity.getNamSinh(), authorEntity.getQueQuan())
            );
    }
}
