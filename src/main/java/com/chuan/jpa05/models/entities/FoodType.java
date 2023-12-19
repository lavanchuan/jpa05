package com.chuan.jpa05.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_food_types")
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ftId;

    @Column(length = 20)
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
