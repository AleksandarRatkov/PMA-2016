package com.example.codecentric.pma_movieapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class Actor {


    private int id;
    private String name;
    private List<Movie> knownFor = new ArrayList<Movie>(); //TODO razmisli da li ces ovako predstavljati filmove ili preko stringa samo nazive


    public Actor() {
    }

    public Actor(int id, String name, List<Movie> knownFor) {
        this.id = id;
        this.name = name;
        this.knownFor = knownFor;
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

    public List<Movie> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<Movie> knownFor) {
        this.knownFor = knownFor;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", knownFor=" + knownFor +
                '}';
    }
}

