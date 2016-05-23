package com.example.codecentric.pma_movieapp.model;

import java.util.List;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class Genre {

    private int id;
    private String name;

    public Genre(int id) {
        this.id = id;
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static class GenreResult {
        private List<Genre> results;

        public List<Genre> getResults() {
            return results;
        }
    }
}
