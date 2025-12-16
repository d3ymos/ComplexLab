package service;

import model.Car;
import model.Doll;
import model.GameRoom;
import model.Toy;
import model.enums.AgeGroup;
import model.enums.Material;
import model.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameRoomManagerTest {

    private GameRoomManager manager;
    private GameRoom gameRoom;

    @BeforeEach
    void setUp() {
        // Перед кожним тестом створюємо чисту кімнату з бюджетом 1000
        gameRoom = new GameRoom(1000.0);
        manager = new GameRoomManager(gameRoom);
    }

    @Test
    void testAddToyWithinBudget() {
        Toy car = new Car("TestCar", 500.0, AgeGroup.CHILD, Size.SMALL);
        boolean result = manager.addToyToRoom(car);

        Assertions.assertTrue(result, "Іграшка мала бути додана");
        Assertions.assertEquals(1, gameRoom.getToys().size());
        Assertions.assertEquals(500.0, manager.calculateTotalCost());
    }

    @Test
    void testAddToyOverBudget() {
        manager.addToyToRoom(new Car("Cheap", 800.0, AgeGroup.CHILD, Size.SMALL));

        // 2 яка перевищить ліміт (800 + 300 > 1000)
        Toy expensive = new Doll("Expensive", 300.0, AgeGroup.CHILD, Material.PLASTIC);
        boolean result = manager.addToyToRoom(expensive);

        Assertions.assertFalse(result, "Іграшка не мала бути додана через бюджет");
        Assertions.assertEquals(1, gameRoom.getToys().size()); // Має залишитись одна
    }

    @Test
    void testSortByPrice() {
        manager.addToyToRoom(new Car("Cheap", 100.0, AgeGroup.CHILD, Size.SMALL));
        manager.addToyToRoom(new Car("Expensive", 500.0, AgeGroup.CHILD, Size.SMALL));
        manager.addToyToRoom(new Car("Medium", 300.0, AgeGroup.CHILD, Size.SMALL));

        manager.sortByPrice();

        List<Toy> toys = gameRoom.getToys();
        Assertions.assertEquals(100.0, toys.get(0).getPrice());
        Assertions.assertEquals(300.0, toys.get(1).getPrice());
        Assertions.assertEquals(500.0, toys.get(2).getPrice());
    }

    @Test
    void testFindToysByRange() {
        manager.addToyToRoom(new Car("Cheap", 50.0, AgeGroup.CHILD, Size.SMALL));   // Не підходить
        manager.addToyToRoom(new Car("Target", 150.0, AgeGroup.CHILD, Size.SMALL)); // Підходить
        manager.addToyToRoom(new Car("Expensive", 500.0, AgeGroup.CHILD, Size.SMALL)); // Не підходить

        Assertions.assertDoesNotThrow(() -> manager.findAndShowToysByRange(100, 200));
    }
}