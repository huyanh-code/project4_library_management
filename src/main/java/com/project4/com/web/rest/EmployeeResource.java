package com.project4.com.web.rest;

import com.project4.com.service.EmployeesService;
import com.project4.com.service.InvalidInputException;
import com.project4.com.service.ReadersService;
import com.project4.com.service.dto.EmployeesDTO;
import com.project4.com.service.dto.ReadersDTO;
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
@RequestMapping("/api/employee")
public class EmployeeResource {

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

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeesService employeesService;

    public EmployeeResource(EmployeesService employeesService) {
        this.employeesService = employeesService;
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
     * {@code POST /api/readers} : create a new reader.
     *
     * @param employeesDTO the reader data to be created.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reader.
     */
    @PostMapping
    public ResponseEntity<EmployeesDTO> create(@RequestBody EmployeesDTO employeesDTO) {
        LOG.debug("REST request to save a new reader");

        // Call the service layer to add the reader
        EmployeesDTO result = employeesService.addEmployee(employeesDTO);

        // Set the URI for the newly created reader
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getMaNV()).toUri();

        // Return a ResponseEntity with status 201 (Created)
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping
    public ResponseEntity<EmployeesDTO> update(@RequestBody EmployeesDTO employeesDTO) throws InvalidInputException {
        LOG.debug("REST request to update");

        // Call the service layer to add the reader
        var result = employeesService.update(employeesDTO);

        return ResponseUtil.wrapOrNotFound(
            Optional.of(result),
            HeaderUtil.createAlert(applicationName, "reader.updated", String.valueOf(employeesDTO.getMaNV()))
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployeesDTO> delete(@PathVariable("id") Integer employeeId) throws InvalidInputException {
        LOG.debug("REST request to delete id=" + employeeId);

        // Call the service layer to add the reader
        employeesService.delete(employeeId);

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createAlert(applicationName, "employee.deleted", String.valueOf(employeeId)))
            .build();
    }

    @GetMapping
    public ResponseEntity<Page<EmployeesDTO>> search(@ParameterObject Pageable pageable, String query) {
        LOG.debug("REST request to search books");

        final Page<EmployeesDTO> page = employeesService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
