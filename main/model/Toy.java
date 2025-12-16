package model;

import model.enums.AgeGroup;

public abstract class Toy implements Comparable<Toy> {
    protected String name;
    protected double price;
    protected AgeGroup ageGroup;

    public Toy(String name, double price, AgeGroup ageGroup) {
        this.name = name;
        this.price = price;
        this.ageGroup = ageGroup;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public AgeGroup getAgeGroup() { return ageGroup; }

    @Override
    public int compareTo(Toy other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Toy {name='%s', price=%.2f, age=%s}", name, price, ageGroup);
    }
}