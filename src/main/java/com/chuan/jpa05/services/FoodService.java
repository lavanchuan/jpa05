package com.chuan.jpa05.services;

import com.chuan.jpa05.exceptions.FoodNotFoundException;
import com.chuan.jpa05.exceptions.RecipeNotFoundException;
import com.chuan.jpa05.models.dtos.FoodDTO;
import com.chuan.jpa05.models.dtos.FoodRecipesDTO;
import com.chuan.jpa05.models.dtos.RecipeDTO;
import com.chuan.jpa05.models.entities.Food;
import com.chuan.jpa05.models.entities.FoodType;
import com.chuan.jpa05.models.entities.Recipe;
import com.chuan.jpa05.models.mappers.mapstructs.FoodMapper;
import com.chuan.jpa05.models.mappers.mapstructs.FoodTypeMapper;
import com.chuan.jpa05.models.mappers.mapstructs.mapper.RecipeMapper;
import com.chuan.jpa05.repositories.contexts.DbContext;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService implements IFoodService, IFoodTypeService {

    @Autowired
    public DbContext dbContext;


    @Override
    public ResponseEntity<?> addFood(@Valid FoodDTO foodDTO) {
        if (dbContext.foodRepository.existsById(
                FoodMapper.INSTANCE.toEntity(foodDTO).getfId()))
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Id is exists");

        // In Foodmapper checked exists ftId !

        // add
        return ResponseEntity.ok(dbContext.foodRepository
                .save(com.chuan.jpa05.models.mappers.mapstructs.mapper.FoodMapper
                        .dtoToEntity(foodDTO, dbContext)));
    }

    @Override
    public ResponseEntity<?> addRecipe(@Valid RecipeDTO recipeDTO) {
        if (dbContext.recipeRepository.existsById(recipeDTO.getrId()))
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Id recipe is exists");

        Recipe entity = RecipeMapper.dtoToEntity(recipeDTO, dbContext);

        Food food = entity.getFood();
        if (food.getGuidMake() == null) food.setGuidMake("");
        food.setGuidMake(String.format("%s%s : %s %s\n",
                entity.getFood().getGuidMake(),
                entity.getMeterial().getmName(),
                entity.getQuantity(),
                entity.getUnitCalc()));

        dbContext.foodRepository.save(food);

        entity.setFood(food);

        entity = dbContext.recipeRepository.save(entity);

        return ResponseEntity.ok(RecipeMapper.entityToDTO(entity));
    }

    @Override
    public ResponseEntity<?> addRecipes(List<@Valid RecipeDTO> dtos, int fId) {
        if (!dbContext.foodRepository.existsById(fId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not found food: " + fId);

        for (RecipeDTO dto : dtos) {
            dto.setfId(fId);
            addRecipe(dto);
        }
        // return list recipe of food
        List<RecipeDTO> dtosResult = new ArrayList<>();
        for (Recipe recipe : dbContext.recipeRepository.findAll()) {
            if (recipe.getFood().getfId() == fId) dtosResult.add(RecipeMapper.entityToDTO(recipe));
        }
        return ResponseEntity.ok(dtosResult);
    }

    @Override
    public ResponseEntity<?> deleteFoodTypeById(int ftId) {
        if (!dbContext.foodTypeRepository.existsById(ftId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id foodtype is not exists");
        // Remove food type then will delete foods is foodtype and delete recipe of this foods
        for (int fId : dbContext.foodRepository.findIdFoodsByFTID(ftId)) {
            dbContext.recipeRepository.deleteByFID(fId);
        }

        dbContext.foodRepository.deleteByFTID(ftId);

        dbContext.foodTypeRepository.deleteById(ftId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> findFoodByNameAndMeterial(String fName, String mName) {

        List<FoodDTO> dtos = new ArrayList<>();
        List<Integer> fIdList = dbContext.recipeRepository.findFoodByNameAndMeterial(fName, mName);
        for (int fId : fIdList) {
            dtos.add(com.chuan.jpa05.models.mappers.mapstructs.mapper.FoodMapper.entityToDTO(
                    dbContext.foodRepository.findById(fId)
                            .orElseThrow(() -> new FoodNotFoundException(fId))
            ));
        }

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<?> addFoodRecipes(FoodRecipesDTO foodRecipesDTO) {

        if (dbContext.foodRepository.existsById(foodRecipesDTO.getFoodDTO().getFtId()))
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Food Id is exists: " + foodRecipesDTO.getFoodDTO().getfId());

        Food entity = dbContext.foodRepository.save(
                com.chuan.jpa05.models.mappers.mapstructs.mapper.FoodMapper.dtoToEntity(
                        foodRecipesDTO.getFoodDTO(), dbContext
                ));
//
        List<RecipeDTO> dtos = foodRecipesDTO.getRecipeDTOS();
        List<Recipe> entities = new ArrayList<>();
        int fId = entity.getfId();
        for (RecipeDTO dto : dtos) {
            RecipeDTO temp = dto;
            if (dto.getfId() != fId) {
                temp.setfId(fId);
                dtos.set(dtos.indexOf(dto), temp);
            }
            entities.add(RecipeMapper.dtoToEntity(temp, dbContext));
        }
//
        dbContext.recipeRepository.saveAll(entities);

        // update makeguid
        StringBuilder guidMake = new StringBuilder();
        for (int id : dbContext.recipeRepository.findRIDByFID(fId)) {
            guidMake.append(String.format("\n%s : %d %s",
                    dbContext.recipeRepository.findMMeterialNameByRID(id),
                    dbContext.recipeRepository.findById(id)
                            .orElseThrow(() -> new RecipeNotFoundException(id)).getQuantity(),
                    dbContext.recipeRepository.findById(id)
                            .orElseThrow(() -> new RecipeNotFoundException(id)).getUnitCalc()));
        }
        entity.setGuidMake(guidMake.toString());
        dbContext.foodRepository.save(entity);

        return findFoodRecipes(fId);
    }

    @Override
    public ResponseEntity<?> findFoodRecipes(int fId) {
        if (!dbContext.foodRepository.existsById(fId)) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Could not found food: " + fId);
        StringBuilder sb = new StringBuilder();
        sb.append(dbContext.foodRepository.findById(fId)
                .orElseThrow(() -> new FoodNotFoundException(fId)).getfName());
        for (int id : dbContext.recipeRepository.findRIDByFID(fId)) {
            sb.append(String.format("\n%s - %d - %s",
                    dbContext.recipeRepository.findMMeterialNameByRID(id),
                    dbContext.recipeRepository.findById(id)
                            .orElseThrow(() -> new RecipeNotFoundException(id)).getQuantity(),
                    dbContext.recipeRepository.findById(id)
                            .orElseThrow(() -> new RecipeNotFoundException(id)).getUnitCalc()));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(sb);
    }

    @Override
    public ResponseEntity<?> findFoodByMNames(String condition){
        String[] meterials = condition.split(" or ");
        List<Integer> fIdList = new ArrayList<>();
        for(String con : meterials){
            fIdList.addAll(dbContext.foodRepository.findFIDsByMNAME(con).stream()
                    .filter(item -> !fIdList.contains(item))
                    .collect(Collectors.toList()));
        }
        List<FoodDTO> foodDTOS = new ArrayList<>();
        for(int id : fIdList){
            foodDTOS.add(com.chuan.jpa05.models.mappers.mapstructs.mapper.FoodMapper.entityToDTO(
                    dbContext.foodRepository.findById(id)
                            .orElseThrow(()->new FoodNotFoundException(id))
            ));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodDTOS);
    }
}
