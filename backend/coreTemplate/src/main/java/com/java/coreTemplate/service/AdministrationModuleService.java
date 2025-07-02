package com.java.coreTemplate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.coreTemplate.repository.AdministrationModuleRepository;
import com.java.coreTemplate.model.dto.AdministrationModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdministrationModuleService {

    private final AdministrationModuleRepository repository;

    public AdministrationModuleService(AdministrationModuleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @CacheEvict(value = "administrationModules", allEntries = true)
    public AdministrationModule save(AdministrationModule entity) {
        return repository.save(entity);
    }

    @Cacheable(value = "administrationModules", key = "#id")
    public Optional<AdministrationModule> findById(Long id) {
        return repository.findById(id);
    }

    @Cacheable("administrationModules")
    public List<AdministrationModule> findAll() {
        return repository.findAll();
    }

    public Page<AdministrationModule> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable(value = "administrationModules", key = "'active'")
    public List<AdministrationModule> findAllActive() {
        return repository.findByIsActiveTrue();
    }

    @Transactional
    @CacheEvict(value = "administrationModules", key = "#id")
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @CacheEvict(value = "administrationModules", allEntries = true)
    public AdministrationModule update(AdministrationModule entity) {
        return repository.save(entity);
    }

    @Cacheable(value = "administrationModules", key = "'count'")
    public long count() {
        return repository.count();
    }

    @Cacheable(value = "administrationModules", key = "#name")
    public Optional<AdministrationModule> findByName(String name) {
        return repository.findByName(name);
    }
}