package com.yuri.elearning.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import lombok.Data;

@Data
@Entity(primaryKeys = {"uid", "cid"})
public class Purchase {
    @NonNull
    public Integer uid;

    @NonNull
    public Integer cid;

    public Purchase(@NonNull Integer uid, @NonNull Integer cid) {
        this.uid = uid;
        this.cid = cid;
    }
}
