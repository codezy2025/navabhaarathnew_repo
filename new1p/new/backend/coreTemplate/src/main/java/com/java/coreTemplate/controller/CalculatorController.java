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

    @PostMapping("/add")
    public ResponseEntity<Calculator> addNumbers(@RequestBody Calculator calculator) {
        return ResponseEntity.ok(service.add(calculator));
    }

    @PostMapping("/subtract")
    public ResponseEntity<Calculator> subtractNumbers(@RequestBody Calculator calculator) {
        return ResponseEntity.ok(service.subtract(calculator));
    }

    @PostMapping("/multiply")
    public ResponseEntity<Calculator> multiplyNumbers(@RequestBody Calculator calculator) {
        return ResponseEntity.ok(service.multiply(calculator));
    }

    @PostMapping("/divide")
    public ResponseEntity<Calculator> divideNumbers(@RequestBody Calculator calculator) {
        try {
            return ResponseEntity.ok(service.divide(calculator));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest()
                    .header("X-Error-Message", "Division by zero is not allowed")
                    .build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<Page<Calculator>> getCalculationHistory(
            @PageableDefault(size = 10, sort = "timestamp", direction = Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.getCalculationHistory(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalculation(@PathVariable Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}