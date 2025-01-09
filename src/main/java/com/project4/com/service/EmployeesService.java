package com.project4.com.service;

import com.project4.com.domain.Employees;
import com.project4.com.domain.Readers;
import com.project4.com.repository.EmployeesRepository;
import com.project4.com.repository.ReadersRepository;
import com.project4.com.service.dto.EmployeesDTO;
import com.project4.com.service.dto.ReadersDTO;
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
public class EmployeesService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeesService.class);
    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    /**
     * Add a new employee.
     *
     * @param dto the employee details to be added
     * @return the added employee as a DTO
     */
    @Transactional
    public EmployeesDTO addEmployee(EmployeesDTO dto) {
        Employees entity = new Employees();
        entity.setTenNV(dto.getTenNV());
        entity.setNamSinh(dto.getNamSinh());
        entity.setGioiTinh(dto.getGioiTinh());
        entity.setDiaChi(dto.getDiaChi());
        entity.setSdt(dto.getSdt());
        entity.setTrangThai(1); // 0: inactive, 1: active

        var createdEntity = employeesRepository.saveAndFlush(entity);
        LOG.debug("Created Information: {}", createdEntity);
        return mapEntityToDto(createdEntity);
    }

    private EmployeesDTO mapEntityToDto(Employees entity) {
        return new EmployeesDTO(
            entity.getMaNV(),
            entity.getTenNV(),
            entity.getNamSinh(),
            entity.getGioiTinh(),
            entity.getDiaChi(),
            entity.getSdt()
        );
    }

    /**
     * Update employee.
     *
     * @param dto the employee details to be update
     * @return the added employee as a DTO
     */

    public EmployeesDTO update(EmployeesDTO dto) throws InvalidInputException {
        var existingEntity = employeesRepository.findById(dto.getMaNV());

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found employee id=" + dto.getMaNV());
        }

        var entity = existingEntity.get();
        entity.setTenNV(dto.getTenNV());
        entity.setNamSinh(dto.getNamSinh());
        entity.setGioiTinh(dto.getGioiTinh());
        entity.setDiaChi(dto.getDiaChi());
        entity.setSdt(dto.getSdt());

        var updatedEntity = employeesRepository.saveAndFlush(entity);

        return mapEntityToDto(updatedEntity);
    }

    /**
     * Delete employee
     *
     * @param employeeId the author details to be deleted
     */
    public void delete(Integer employeeId) throws InvalidInputException {
        var existingEntity = employeesRepository.findById(employeeId);

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found reader id=" + employeeId);
        }

        employeesRepository.delete(existingEntity.get());
    }

    public Page<EmployeesDTO> search(String query, Pageable pageable) {
        Page<Object[]> rawResults = employeesRepository.search(query, pageable);
        // Convert Object[] to EmployeeDTO
        Page<EmployeesDTO> dtoPage = rawResults.map(objects ->
            new EmployeesDTO(
                (Integer) objects[0], // MaNV
                (String) objects[1], // tenNV
                (String) objects[2], // NamSinh
                (String) objects[3], // gioiTinh
                (String) objects[4], // diachi
                (String) objects[5] // sdt
            )
        );
        return dtoPage;
    }
}
