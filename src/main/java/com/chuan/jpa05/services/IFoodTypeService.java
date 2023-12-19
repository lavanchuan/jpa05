package com.chuan.jpa05.services;

import org.springframework.http.ResponseEntity;

public interface IFoodTypeService {
    ResponseEntity<?> deleteFoodTypeById(int ftId);
}
