package com.chuan.jpa05.models.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NotFound;

public class MeterialDTO {
    int mId;

    @NotNull
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
