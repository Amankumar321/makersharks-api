package com.makersharks.api.services;

import com.makersharks.api.model.Supplier;
import com.makersharks.api.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SupplierServiceTests {

    @InjectMocks
    private SupplierService supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    public SupplierServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchSuppliersWithResults() {
        // Arrange
        Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(1L);
        supplier1.setCompanyName("Company A");
        supplier1.setWebsite("www.companya.com");
        supplier1.setLocation("India");
        supplier1.setNatureOfBusiness("small_scale");
        supplier1.setManufacturingProcesses("3d_printing");

        Supplier supplier2 = new Supplier();
        supplier2.setSupplierId(2L);
        supplier2.setCompanyName("Company B");
        supplier2.setWebsite("www.companyb.com");
        supplier2.setLocation("India");
        supplier2.setNatureOfBusiness("small_scale");
        supplier2.setManufacturingProcesses("moulding");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> suppliersPage = new PageImpl<>(Arrays.asList(supplier1, supplier2), pageable, 2);

        when(supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(
                "India", "small_scale", "3d_printing", pageable)).thenReturn(suppliersPage);

        // Act
        Page<Supplier> result = supplierService.searchSuppliers("India", "small_scale", "3d_printing", 0, 10);

        // Assert
        assertEquals(2, result.getTotalElements());
        assertEquals("Company A", result.getContent().get(0).getCompanyName());
        assertEquals("Company B", result.getContent().get(1).getCompanyName());
    }

    @Test
    void testSearchSuppliersWithNoResults() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> emptySuppliersPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(
                "India", "small_scale", "3d_printing", pageable)).thenReturn(emptySuppliersPage);

        // Act
        Page<Supplier> result = supplierService.searchSuppliers("India", "small_scale", "3d_printing", 0, 10);

        // Assert
        assertEquals(0, result.getTotalElements());
    }
}
