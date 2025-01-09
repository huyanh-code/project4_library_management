package com.project4.com.web.rest;

import com.project4.com.service.BookService;
import com.project4.com.service.InvalidInputException;
import com.project4.com.service.dto.BookDTO;
import com.project4.com.service.dto.EmployeesDTO;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/books")
public class BookResource {

    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList(
            "id",
            "login",
            "firstName",
            "lastName",
            "email",
            "activated",
            "langKey",
            "createdBy",
            "createdDate",
            "lastModifiedBy",
            "lastModifiedDate"
        )
    );

    private static final Logger LOG = LoggerFactory.getLogger(BookResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * {@code GET /admin/users} : get all users with all the details - calling this are only allowed for the administrators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    //    @GetMapping
    //    public ResponseEntity<Page<BookDTO>> getAll(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
    //        LOG.debug("REST request to get all books for an admin");
    //        if (!onlyContainsAllowedProperties(pageable)) {
    //            return ResponseEntity.badRequest().build();
    //        }
    //
    //        final Page<BookDTO> page = bookService.getAllBooks(pageable);
    //        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    //        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    //    }
    //
    //    private boolean onlyContainsAllowedProperties(Pageable pageable) {
    //        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    //    }

    @GetMapping
    public ResponseEntity<Page<BookDTO>> search(@org.springdoc.core.annotations.ParameterObject Pageable pageable, String query) {
        LOG.debug("REST request to search books");

        final Page<BookDTO> page = bookService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {
        LOG.debug("REST request to save a new book");

        // Call the service layer to add the reader
        BookDTO result = bookService.create(bookDTO);

        // Set the URI for the newly created reader
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getMaSoSach()).toUri();

        // Return a ResponseEntity with status 201 (Created)
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO bookDTO) throws InvalidInputException {
        LOG.debug("REST request to update");

        // Call the service layer to add the reader
        var result = bookService.update(bookDTO);

        return ResponseUtil.wrapOrNotFound(
            Optional.of(result),
            HeaderUtil.createAlert(applicationName, "reader.updated", String.valueOf(bookDTO.getMaSoSach()))
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BookDTO> delete(@PathVariable("id") Integer bookId) throws InvalidInputException {
        LOG.debug("REST request to delete id=" + bookId);

        // Call the service layer to add the reader
        bookService.delete(bookId);

        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "book.deleted", String.valueOf(bookId))).build();
    }
}
