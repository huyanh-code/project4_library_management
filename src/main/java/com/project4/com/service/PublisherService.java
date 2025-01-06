package com.project4.com.service;

import com.project4.com.repository.AuthorRepository;
import com.project4.com.repository.BookRepository;
import com.project4.com.repository.PublisherRepository;
import com.project4.com.service.dto.AuthorDTO;
import com.project4.com.service.dto.BookDTO;
import com.project4.com.service.dto.PublisherDTO;
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
public class PublisherService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorService.class);
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional(readOnly = true)
    public Page<PublisherDTO> getAllPublisher(Pageable pageable) {
        return publisherRepository
            .findAll(pageable)
            .map(publisherEntity ->
                new PublisherDTO(
                    publisherEntity.getMaNXB(),
                    publisherEntity.getTenNXB(),
                    publisherEntity.getDiaChi(),
                    publisherEntity.getSdt()
                )
            );
    }
}
