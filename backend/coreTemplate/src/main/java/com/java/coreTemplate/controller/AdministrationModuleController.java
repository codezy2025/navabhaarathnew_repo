package com.java.coreTemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.java.coreTemplate.service.AdministrationModuleService;
import com.java.coreTemplate.model.dto.AdministrationModule;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/administration-modules")
public class AdministrationModuleController {
    
    private final AdministrationModuleService service;
    
    public AdministrationModuleController(AdministrationModuleService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<AdministrationModule> create(@Valid @RequestBody AdministrationModule entity) {
        AdministrationModule savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AdministrationModule> getById(@PathVariable Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<Page<AdministrationModule>> getAll(
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        Page<AdministrationModule> modules = service.findAll(pageable);
        return ResponseEntity.ok(modules);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AdministrationModule> update(
            @PathVariable Long id, 
            @Valid @RequestBody AdministrationModule entity) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entity.setId(id);
        AdministrationModule updatedEntity = service.save(entity);
        return ResponseEntity.ok(updatedEntity);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<AdministrationModule> partialUpdate(
            @PathVariable Long id,
            @RequestBody AdministrationModule partialEntity) {
        return service.partialUpdate(id, partialEntity)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<AdministrationModule>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<AdministrationModule> results = service.search(name, code, pageable);
        return ResponseEntity.ok(results);
    }
}