package com.chuan.jpa05.controllers;

import com.chuan.jpa05.exceptions.FoodTypeNotFoundException;
import com.chuan.jpa05.models.dtos.FoodTypeDTO;
import com.chuan.jpa05.models.entities.FoodType;
import com.chuan.jpa05.models.mappers.mapstructs.FoodTypeMapper;
import com.chuan.jpa05.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/foodtype")
public class FoodTypeController {

    @Autowired
    FoodService foodService;

    // GET
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findFoodTypeById(@PathVariable int id){
        return ResponseEntity.ok(foodService.dbContext.foodTypeRepository
                .findById(id).orElseThrow(()->new FoodTypeNotFoundException(id)));
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> findAll(){
        List<FoodTypeDTO> dtos = new ArrayList<>();
        for(FoodType entity : foodService.dbContext.foodTypeRepository.findAll()){
            dtos.add(FoodTypeMapper.INSTANCE.toDTO(entity));
        }
        return ResponseEntity.ok(dtos);
    }

    // POST

    @PostMapping("/add")
    public ResponseEntity<?> addFoodType(@RequestBody FoodTypeDTO dto){
        return ResponseEntity.ok(
                FoodTypeMapper.INSTANCE.toDTO(
                        foodService.dbContext.foodTypeRepository
                                .save(FoodTypeMapper.INSTANCE.toEntity(dto))
                )
        );
    }

    // PUT

    // DELETE

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFoodType(@PathVariable int id){
        return foodService.deleteFoodTypeById(id);
    }
}
