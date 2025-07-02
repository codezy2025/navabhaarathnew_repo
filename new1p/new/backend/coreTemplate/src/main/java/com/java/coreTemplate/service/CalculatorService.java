package com.java.coreTemplate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import com.java.coreTemplate.repository.CalculatorRepository;
import com.java.coreTemplate.model.dto.Calculator;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@Transactional(readOnly = true)
public class CalculatorService {

    private final CalculatorRepository repository;

    public CalculatorService(CalculatorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Calculator save(@Valid Calculator entity) {
        return repository.save(entity);
    }

    @Cacheable("calculatorCache")
    public Optional<Calculator> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Calculator> findAll() {
        return repository.findAll();
    }

    public List<Calculator> findAllActive() {
        return repository.findByIsActiveTrue();
    }

    @Cacheable("calculatorOperationsCache")
    public double performOperation(double operand1, double operand2, String operation) {
        return switch (operation.toLowerCase()) {
            case "add" -> operand1 + operand2;
            case "subtract" -> operand1 - operand2;
            case "multiply" -> operand1 * operand2;
            case "divide" -> {
                if (operand2 == 0) throw new ArithmeticException("Division by zero");
                yield operand1 / operand2;
            }
            default -> throw new UnsupportedOperationException("Operation not supported: " + operation);
        };
    }

    @Transactional
    public Calculator logCalculation(Calculator calculator) {
        return repository.save(calculator);
    }

    public List<Calculator> findByOperationType(String operationType) {
        return repository.findByOperationType(operationType);
    }
}