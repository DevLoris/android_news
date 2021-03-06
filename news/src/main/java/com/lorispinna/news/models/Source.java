package com.lorispinna.news.models;

import com.google.gson.annotations.Expose;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Source {
    //@Expose(deserialize = false, serialize = false)
    @PrimaryKey(autoGenerate = true)
    public Integer id_src;

    public String name;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Integer getId_src() {
        return id_src;
    }

    public void setId_src(Integer  id_src) {
        this.id_src = id_src;
    }
}
