package com.java.coreTemplate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.coreTemplate.repository.CalculatorRepository;
import com.java.coreTemplate.model.dto.Calculator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CalculatorService {

    private final CalculatorRepository repository;

    public CalculatorService(CalculatorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @CacheEvict(value = "calculations", allEntries = true)
    public Calculator save(Calculator entity) {
        return repository.save(entity);
    }

    @Cacheable("calculations")
    public Optional<Calculator> findById(Long id) {
        return repository.findById(id);
    }

    @Cacheable("calculations")
    public List<Calculator> findAll() {
        return repository.findAll();
    }

    @Cacheable("calculations")
    public Page<Calculator> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    @CacheEvict(value = "calculations", allEntries = true)
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    @Transactional
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    @Cacheable(value = "calculations", key = "#root.methodName + #a + #b")
    public double calculatePercentage(double a, double b) {
        return (a * b) / 100;
    }

    @Cacheable("calculations")
    public List<Calculator> findAllActive() {
        return repository.findByIsActiveTrue();
    }

    @Cacheable("calculations")
    public List<Calculator> findByOperationType(String operationType) {
        return repository.findByOperationType(operationType);
    }
}