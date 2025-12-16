package model;

import java.util.ArrayList;
import java.util.List;

public class GameRoom {
    private List<Toy> toys;
    private double budget;

    public GameRoom(double budget) {
        this.budget = budget;
        this.toys = new ArrayList<>();
    }

    public List<Toy> getToys() {
        return toys;
    }

    public double getBudget() {
        return budget;
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }
}