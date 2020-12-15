package com.example.app_ban_hang.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.app_ban_hang.model.DogClass;

import java.util.List;

@Dao
public interface DogDao {
    @Query("SELECT * FROM DOGCLASS")
    public List<DogClass> getAllDogs();

    @Insert
    public void insertDog(DogClass... dogBreed);

    @Query("DELETE FROM DogClass")
    public void deleteAll();

    @Query("SELECT * FROM DogClass WHERE Name LIKE '%' || :name || '%'")
    public  List<DogClass> searchByName(String name);
}
