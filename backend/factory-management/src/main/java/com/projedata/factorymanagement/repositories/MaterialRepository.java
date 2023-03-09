package com.projedata.factorymanagement.repositories;

import com.projedata.factorymanagement.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {

    Optional<Material> findByName(String name);
}
