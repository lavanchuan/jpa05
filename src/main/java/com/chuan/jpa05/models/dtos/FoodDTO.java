package com.chuan.jpa05.models.dtos;

import com.chuan.jpa05.exceptions.FoodTypeNotFoundException;
import com.chuan.jpa05.models.mappers.mapstructs.FoodTypeMapper;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class FoodDTO implements Serializable {

    int fId;

    int ftId;

    @NotNull
    String fName;

    float fPrice;

    String fIntro;

    String guidMake;

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public int getFtId() {
        return ftId;
    }

    public void setFtId(int ftId) {
        this.ftId = ftId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public float getfPrice() {
        return fPrice;
    }

    public void setfPrice(float fPrice) {
        this.fPrice = fPrice;
    }

    public String getfIntro() {
        return fIntro;
    }

    public void setfIntro(String fIntro) {
        this.fIntro = fIntro;
    }

    public String getGuidMake() {
        return guidMake;
    }

    public void setGuidMake(String guidMake) {
        this.guidMake = guidMake;
    }
}
