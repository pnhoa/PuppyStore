package com.example.app_ban_hang.model;

import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity()
public class DogClass implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "Id")
    @SerializedName("id")
    private  int id;

    @ColumnInfo(name = "Name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "Bred_for")
    @SerializedName("bred_for")
    private String bred_for;

    @ColumnInfo(name = "Breed_group")
    @SerializedName("breed_group")
    private String breed_group;


    @ColumnInfo(name = "Life_span")
    @SerializedName("life_span")
    private String lifeSpan;

    @ColumnInfo(name = "Origin")
    @SerializedName("origin")
    private String origin;

    @ColumnInfo(name = "Temperament")
    @SerializedName("temperament")
    private String temperament;

    @ColumnInfo(name = "Url_image")
    @SerializedName("url")
    private String url;

    @ColumnInfo(name = "Dog_count")
   // @SerializedName("Dog_count")
    private int dog_count;

    @ColumnInfo(name = "Dog_money")
    //@SerializedName("Dog_money")

    private int money;

    private boolean showMenu = false;


    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public boolean isShowMenu() {
        return showMenu;
    }


    public int getDog_count() {
        return dog_count;
    }

    public void setDog_count(int dog_count) {
        this.dog_count = dog_count;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
