package com.java.coreTemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.java.coreTemplate.model.dto.AdministrationModule;

import java.util.List;
import java.util.Optional;

public interface AdministrationModuleRepository extends 
    JpaRepository<AdministrationModule, Long> {
    
    // Find by module name (case-insensitive)
    Optional<AdministrationModule> findByNameIgnoreCase(String name);
    
    // Find all active modules
    List<AdministrationModule> findByActiveTrue();
    
    // Find modules by type using JPQL with named parameter
    @Query("SELECT am FROM AdministrationModule am WHERE am.type = :type ORDER BY am.name")
    List<AdministrationModule> findByType(@Param("type") String type);
    
    // Find modules with names containing a string (case-insensitive)
    List<AdministrationModule> findByNameContainingIgnoreCase(String nameFragment);
    
    // Check if a module with given name exists (case-insensitive)
    boolean existsByNameIgnoreCase(String name);
    
    // Find modules with specific IDs using IN clause
    @Query("SELECT am FROM AdministrationModule am WHERE am.id IN :ids")
    List<AdministrationModule> findByIds(@Param("ids") List<Long> ids);
    
    // Custom projection to get only name and description
    @Query("SELECT new com.java.coreTemplate.model.dto.AdministrationModule(am.name, am.description) FROM AdministrationModule am WHERE am.active = true")
    List<AdministrationModule> findActiveModulesNameAndDescription();
    
    // Native query example
    @Query(value = "SELECT * FROM administration_modules WHERE created_at > CURRENT_DATE - INTERVAL '30 days'", nativeQuery = true)
    List<AdministrationModule> findRecentModules();
    
    // Update status for multiple modules
    @Modifying
    @Query("UPDATE AdministrationModule am SET am.active = :active WHERE am.id IN :ids")
    int updateActiveStatus(@Param("ids") List<Long> ids, @Param("active") boolean active);
}