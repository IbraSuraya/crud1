package com.fikupn.crud1.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String fullName;

    public String email;
    public String nim;
//    public String kategori;
    public String beratKG;
    public String tinggiCM;
}
