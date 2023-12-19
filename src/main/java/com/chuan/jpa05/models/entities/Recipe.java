package com.chuan.jpa05.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int rId;

    int quantity;

    @Column(length = 10)
    String unitCalc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mId")
    Meterial meterial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fId")
    Food food;

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitCalc() {
        return unitCalc;
    }

    public void setUnitCalc(String unitCalc) {
        this.unitCalc = unitCalc;
    }

    public Food getFood() {
        return food;
    }

    public Meterial getMeterial() {
        return meterial;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setMeterial(Meterial meterial) {
        this.meterial = meterial;
    }
}
