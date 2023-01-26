package com.projetada.factorymanagement.repositories;

import com.projetada.factorymanagement.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {

    boolean existsByName(String name);
}