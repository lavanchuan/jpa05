package com.chuan.jpa05.repositories;

import com.chuan.jpa05.models.entities.Meterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMeterialRepository extends JpaRepository<Meterial, Integer> {
}
