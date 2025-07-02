package com.java.coreTemplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.java.coreTemplate.service.CalculatorService;
import com.java.coreTemplate.model.dto.Calculator;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {
    private final CalculatorService service;

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Calculator> create(@RequestBody Calculator entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calculator> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Calculator>> getAll(
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calculator> update(
            @PathVariable Long id, 
            @RequestBody Calculator entity) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entity.setId(id);
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Calculator> add(@RequestBody CalculatorRequest request) {
        double result = service.add(request.getOperand1(), request.getOperand2());
        return ResponseEntity.ok(new Calculator(result));
    }

    @PostMapping("/subtract")
    public ResponseEntity<Calculator> subtract(@RequestBody CalculatorRequest request) {
        double result = service.subtract(request.getOperand1(), request.getOperand2());
        return ResponseEntity.ok(new Calculator(result));
    }

    @PostMapping("/multiply")
    public ResponseEntity<Calculator> multiply(@RequestBody CalculatorRequest request) {
        double result = service.multiply(request.getOperand1(), request.getOperand2());
        return ResponseEntity.ok(new Calculator(result));
    }

    @PostMapping("/divide")
    public ResponseEntity<Calculator> divide(@RequestBody CalculatorRequest request) {
        try {
            double result = service.divide(request.getOperand1(), request.getOperand2());
            return ResponseEntity.ok(new Calculator(result));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<Page<Calculator>> getCalculationHistory(
            @PageableDefault(size = 10, sort = "timestamp") Pageable pageable) {
        return ResponseEntity.ok(service.getCalculationHistory(pageable));
    }
}

// Supporting request DTO
class CalculatorRequest {
    private double operand1;
    private double operand2;

    // Getters and setters
    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }
}