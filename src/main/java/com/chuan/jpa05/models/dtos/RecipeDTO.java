package com.chuan.jpa05.models.dtos;

import jakarta.validation.constraints.NotNull;

public class RecipeDTO {
    int rId;
    int fId;
    int mId;
    @NotNull
    int quantity;
    @NotNull
    String unitCalc;

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
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
}
