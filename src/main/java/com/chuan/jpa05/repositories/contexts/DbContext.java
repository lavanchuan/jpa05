package com.chuan.jpa05.repositories.contexts;

import com.chuan.jpa05.repositories.IFoodRepository;
import com.chuan.jpa05.repositories.IFoodTypeRepository;
import com.chuan.jpa05.repositories.IMeterialRepository;
import com.chuan.jpa05.repositories.IRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbContext {
    @Autowired
    public IFoodTypeRepository foodTypeRepository;
    @Autowired
    public IFoodRepository foodRepository;
    @Autowired
    public IMeterialRepository meterialRepository;
    @Autowired
    public IRecipeRepository recipeRepository;
}
