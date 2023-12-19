package com.chuan.jpa05.models.dtos;


import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NotFound;

public class FoodTypeDTO {

    int ftId;

    @NotNull
    String ftName;

    String ftNote;

    public int getFtId() {
        return ftId;
    }

    public void setFtId(int ftId) {
        this.ftId = ftId;
    }

    public String getFtName() {
        return ftName;
    }

    public void setFtName(String ftName) {
        this.ftName = ftName;
    }

    public String getFtNote() {
        return ftNote;
    }

    public void setFtNote(String ftNote) {
        this.ftNote = ftNote;
    }
}
