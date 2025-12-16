package model;

import model.enums.AgeGroup;
import model.enums.Material;

public class Doll extends Toy {
    private Material material;

    public Doll(String name, double price, AgeGroup ageGroup, Material material) {
        super(name, price, ageGroup);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return String.format("Doll {name='%s', price=%.2f, age=%s, material=%s}",
                name, price, ageGroup, material);
    }
}