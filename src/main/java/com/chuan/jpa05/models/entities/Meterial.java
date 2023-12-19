package com.chuan.jpa05.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_meterials")
public class Meterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int mId;

    @Column(length = 20)
    String mName;

    String mNote;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }
}
