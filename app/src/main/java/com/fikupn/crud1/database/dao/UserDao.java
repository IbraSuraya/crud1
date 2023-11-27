package com.fikupn.crud1.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.fikupn.crud1.database.entitas.Users;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<Users> getAll();

    @Query("INSERT INTO users (name,email,nim, beratKG, tinggiCM) VALUES(:name, :email, :nim,  :beratKG, :tinggiCM)")
    void insertAll(String name, String email, String nim, String beratKG, String tinggiCM);

    @Query("UPDATE users SET name=:name, email=:email, nim=:nim, beratKG=:beratKG, tinggiCM=:tinggiCM WHERE uid=:uid")
    void update(int uid, String name, String email, String nim, String beratKG, String tinggiCM);

    @Query("SELECT * FROM users WHERE uid=:uid")
    Users get(int uid);

    @Delete
    void delete(Users users);
}
