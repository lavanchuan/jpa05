package com.chuan.jpa05.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fId;

    @Column(length = 20)
    String fName;

    float fPrice;

    String fIntro;

    String guidMake;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftId")
    FoodType foodType;

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
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

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
}
