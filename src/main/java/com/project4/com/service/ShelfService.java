package com.project4.com.service;

import com.project4.com.repository.ReadersRepository;
import com.project4.com.repository.ShelfRepository;
import com.project4.com.service.dto.ReadersDTO;
import com.project4.com.service.dto.ShelfDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class ShelfService {

    private static final Logger LOG = LoggerFactory.getLogger(ShelfService.class);
    private final ShelfRepository shelfRepository;

    public ShelfService(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getAllShelf(Pageable pageable) {
        return shelfRepository.findAll(pageable).map(shelfEntity -> new ShelfDTO(shelfEntity.getMaKeSach(), shelfEntity.getViTri()));
    }
}
