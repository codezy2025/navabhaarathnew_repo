package com.java.coreTemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.java.coreTemplate.model.dto.Calculator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CalculatorRepository extends JpaRepository<Calculator, Long> {

    // Find calculations by operation type using derived query method
    List<Calculator> findByOperationType(String operationType);

    // Find calculations with result greater than specified value
    List<Calculator> findByResultGreaterThan(Double result);

    // Find calculations performed between two dates
    List<Calculator> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // Find top 5 most recent calculations
    List<Calculator> findTop5ByOrderByCreatedAtDesc();

    // Find calculations by operation type and result range (using JPQL)
    @Query("SELECT c FROM Calculator c WHERE c.operationType = :operationType AND c.result BETWEEN :min AND :max")
    List<Calculator> findByOperationAndResultRange(
            @Param("operationType") String operationType,
            @Param("min") Double min,
            @Param("max") Double max);

    // Find calculation by unique identifier (UUID) if your entity has one
    Optional<Calculator> findByCalculationUuid(String uuid);

    // Count calculations by operation type
    long countByOperationType(String operationType);

    // Check if a calculation exists with the exact parameters
    boolean existsByOperand1AndOperand2AndOperationType(
            Double operand1, 
            Double operand2, 
            String operationType);

    // Delete calculations older than specified date
    void deleteByCreatedAtBefore(LocalDateTime date);

    // Custom projection to get only operation type and result
    @Query("SELECT c.operationType as operationType, c.result as result FROM Calculator c WHERE c.id = :id")
    <T> T findProjectedById(@Param("id") Long id, Class<T> type);
}