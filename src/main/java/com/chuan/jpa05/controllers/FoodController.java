package com.chuan.jpa05.controllers;

import com.chuan.jpa05.exceptions.FoodNotFoundException;
import com.chuan.jpa05.models.dtos.FoodDTO;
import com.chuan.jpa05.models.dtos.FoodRecipesDTO;
import com.chuan.jpa05.models.entities.Food;
import com.chuan.jpa05.models.mappers.mapstructs.mapper.FoodMapper;
import com.chuan.jpa05.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService service;

    // GET
    @GetMapping("/entity-to-dto/{id}")
    public ResponseEntity<?> foodEntityToDTO(@PathVariable int id){
        return ResponseEntity.ok(
                FoodMapper.entityToDTO(service.dbContext.foodRepository.findById(id)
                        .orElseThrow(()->new FoodNotFoundException(id)))
        );
    }

    @GetMapping("/dto-to-entity")
    public ResponseEntity<?> foodDTOToEntity(@RequestBody @Valid FoodDTO foodDTO){
        return ResponseEntity.ok(
                FoodMapper.dtoToEntity(foodDTO, service.dbContext)
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findFoodById(@PathVariable int id){
        return ResponseEntity.ok(
                FoodMapper.entityToDTO(
                        service.dbContext.foodRepository.findById(id)
                                .orElseThrow(()->new FoodNotFoundException(id))
                )
        );
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> findFoodAll(){
        List<FoodDTO> dtos = new ArrayList<>();
        for(Food entity : service.dbContext.foodRepository.findAll()){
            dtos.add(FoodMapper.entityToDTO(entity));
        }
        return ResponseEntity.ok(dtos);
    }

//    @GetMapping("/find/{fName}/{mName}")
//    public ResponseEntity<?> findFoodByNameAndMeterial(@PathVariable String fName, @PathVariable String mName){
//        return service.findFoodByNameAndMeterial(fName, mName);
//    }

    @GetMapping("/find")
    public ResponseEntity<?> findFoodByNameAndMeterial(@RequestParam(name = "fName", value = "") String fName,
                                                       @RequestParam(name = "mName", value = "") String mName){
        return service.findFoodByNameAndMeterial(fName, mName);
    }

    @GetMapping("/recipes/find/{fId}")
    public ResponseEntity<?> findFoodRecipesByFID(@PathVariable int fId){
        return service.findFoodRecipes(fId);
    }

    @GetMapping("/find-byMName/{condition}")
    public ResponseEntity<?> findFoodByMName(@PathVariable("condition") String condition){
        return service.findFoodByMNames(condition);
    }

    // POST
    @PostMapping("/add")
    public ResponseEntity<?> addFood(@RequestBody FoodDTO foodDTO){
        return service.addFood(foodDTO);
    }

    @PostMapping("/add/food-recipes")
    public ResponseEntity<?> addFoodRecipes(@RequestBody FoodRecipesDTO dto){
        return ResponseEntity.ok(service.addFoodRecipes(dto));
    }


}
