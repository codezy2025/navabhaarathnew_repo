package com.java.coreTemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.java.coreTemplate.model.dto.Calculator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CalculatorRepository extends JpaRepository<Calculator, Long> {

    // Find by operation type using derived query
    List<Calculator> findByOperationType(String operationType);

    // Find calculations with result greater than specified value
    List<Calculator> findByResultGreaterThan(Double result);

    // Find calculations performed between two dates
    List<Calculator> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // Find top 5 most recent calculations
    List<Calculator> findTop5ByOrderByCreatedAtDesc();

    // Find by operation type and result range using JPQL
    @Query("SELECT c FROM Calculator c WHERE c.operationType = :operationType AND c.result BETWEEN :min AND :max")
    List<Calculator> findByOperationAndResultRange(
            @Param("operationType") String operationType,
            @Param("min") Double min,
            @Param("max") Double max);

    // Find average result by operation type using projection
    @Query("SELECT c.operationType as operationType, AVG(c.result) as averageResult " +
           "FROM Calculator c GROUP BY c.operationType")
    List<OperationAverage> findAverageResultByOperationType();

    // Find using a case-insensitive search on operation type
    List<Calculator> findByOperationTypeIgnoreCase(String operationType);

    // Find calculations with optional filters using Java 8 Optional
    @Query("SELECT c FROM Calculator c WHERE " +
           "(:operationType IS NULL OR c.operationType = :operationType) AND " +
           "(:minResult IS NULL OR c.result >= :minResult) AND " +
           "(:maxResult IS NULL OR c.result <= :maxResult)")
    List<Calculator> findWithOptionalFilters(
            @Param("operationType") Optional<String> operationType,
            @Param("minResult") Optional<Double> minResult,
            @Param("maxResult") Optional<Double> maxResult);

    // Projection interface for average result query
    interface OperationAverage {
        String getOperationType();
        Double getAverageResult();
    }
}