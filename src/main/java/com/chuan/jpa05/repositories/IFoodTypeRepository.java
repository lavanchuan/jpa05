package com.chuan.jpa05.repositories;

import com.chuan.jpa05.models.entities.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodTypeRepository extends JpaRepository<FoodType, Integer> {
}
