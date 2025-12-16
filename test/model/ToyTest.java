package model;

import model.enums.AgeGroup;
import model.enums.Material;
import model.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToyTest {

    @Test
    void testCarCreation() {
        Car car = new Car("BMW", 200.0, AgeGroup.CHILD, Size.MEDIUM);

        Assertions.assertEquals("BMW", car.getName());
        Assertions.assertEquals(200.0, car.getPrice());
        Assertions.assertEquals(AgeGroup.CHILD, car.getAgeGroup());
        Assertions.assertEquals(Size.MEDIUM, car.getSize());
    }

    @Test
    void testDollCreation() {
        Doll doll = new Doll("Barbie", 150.0, AgeGroup.PRESCHOOL, Material.PLASTIC);

        Assertions.assertEquals("Barbie", doll.getName());
        Assertions.assertEquals(Material.PLASTIC, doll.getMaterial());
    }
}