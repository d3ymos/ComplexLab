package model;

import model.enums.AgeGroup;
import model.enums.Size;

public class Car extends Toy {
    private Size size;

    public Car(String name, double price, AgeGroup ageGroup, Size size) {
        super(name, price, ageGroup);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("Car {name='%s', price=%.2f, age=%s, size=%s}",
                name, price, ageGroup, size);
    }
}