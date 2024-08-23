package com.makersharks.api.controller;

import com.makersharks.api.model.Supplier;
import com.makersharks.api.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public Page<Supplier> querySuppliers(
            @RequestParam @NotNull String location,
            @RequestParam @NotNull String natureOfBusiness,
            @RequestParam @NotNull String manufacturingProcesses,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return supplierService.searchSuppliers(location, natureOfBusiness, manufacturingProcesses, page, size);
    }
}
