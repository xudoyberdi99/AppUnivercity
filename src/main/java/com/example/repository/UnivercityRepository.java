package com.example.repository;

import com.example.entity.Univercity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnivercityRepository extends JpaRepository<Univercity,Integer> {
    boolean existsByName(String name);
}
