package com.example.comicsappandroid.data.api.models;

/**
 * Character Model
 * https://comicvine.gamespot.com/api/documentation
 */
public class CharacterComics {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private Integer id;
}
