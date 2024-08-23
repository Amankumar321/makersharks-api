package com.makersharks.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.makersharks.api.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcesses(
            String location, String natureOfBusiness, String manufacturingProcesses, Pageable pageable);
}
