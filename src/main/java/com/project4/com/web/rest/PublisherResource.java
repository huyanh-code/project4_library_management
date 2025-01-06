package com.project4.com.web.rest;

import com.project4.com.service.InvalidInputException;
import com.project4.com.service.PublishService;
import com.project4.com.service.dto.PublisherDTO;
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
@RequestMapping("/api/publisher")
public class PublisherResource {

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

    private static final Logger LOG = LoggerFactory.getLogger(PublisherResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublishService publishService;

    public PublisherResource(PublishService publishService) {
        this.publishService = publishService;
    }

    /**
     * {@code GET /admin/users} : get all users with all the details - calling this are only allowed for the administrators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }

    /**
     * {@code POST /api/publisher} : create a new reader.
     *
     * @param publisherDTO the reader data to be created.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reader.
     */
    @PostMapping
    public ResponseEntity<PublisherDTO> create(@RequestBody PublisherDTO publisherDTO) {
        LOG.debug("REST request to save a new publisher " + publisherDTO.toString());

        // Call the service layer to add the reader
        PublisherDTO result = publishService.addPublisher(publisherDTO);

        // Set the URI for the newly created reader
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getMaNXB()).toUri();

        // Return a ResponseEntity with status 201 (Created)
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping
    public ResponseEntity<PublisherDTO> update(@RequestBody PublisherDTO publisherDTO) throws InvalidInputException {
        LOG.debug("REST request to update");

        // Call the service layer to add the reader
        var result = publishService.update(publisherDTO);

        return ResponseUtil.wrapOrNotFound(
            Optional.of(result),
            HeaderUtil.createAlert(applicationName, "reader.updated", String.valueOf(publisherDTO.getMaNXB()))
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PublisherDTO> delete(@PathVariable("id") Integer publisherId) throws InvalidInputException {
        LOG.debug("REST request to delete id=" + publisherId);

        // Call the service layer to add the reader
        publishService.delete(publisherId);

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createAlert(applicationName, "publisher.deleted", String.valueOf(publisherId)))
            .build();
    }

    @GetMapping
    public ResponseEntity<Page<PublisherDTO>> search(@ParameterObject Pageable pageable, String query) {
        LOG.debug("REST request to search publisher");

        final Page<PublisherDTO> page = publishService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
