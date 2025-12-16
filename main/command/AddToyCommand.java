package command;

import model.Car;
import model.Doll;
import model.Toy;
import model.enums.AgeGroup;
import model.enums.Material;
import model.enums.Size;
import service.GameRoomManager;

public class AddToyCommand implements Command {
    private final GameRoomManager manager;

    // Приймаємо менеджера через конструктор
    public AddToyCommand(GameRoomManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDesc() {
        return "Додати іграшку. Формат: type(car/doll) name price ageGroup(toddler/child/teen) param(size/material)";
    }

    @Override
    public void execute(String params) {
        if (params == null || params.isEmpty()) {
            System.out.println("Помилка: введіть параметри іграшки.");
            return;
        }

        try {
            // Розбиваємо рядок параметрів по пробілах
            String[] parts = params.split(" ");

            // Очікуємо мінімум 5 параметрів
            if (parts.length < 5) {
                System.out.println("Помилка: недостатньо параметрів. Дивіться help.");
                return;
            }

            String type = parts[0].toLowerCase();
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            // Перетворюємо рядок у Enum (TODDLER, CHILD тощо)
            AgeGroup age = AgeGroup.valueOf(parts[3].toUpperCase());
            String specialParam = parts[4].toUpperCase();

            Toy newToy = null;

            switch (type) {
                case "car":
                    Size size = Size.valueOf(specialParam); // SMALL, MEDIUM, LARGE
                    newToy = new Car(name, price, age, size);
                    break;
                case "doll":
                    Material material = Material.valueOf(specialParam); // PLASTIC, WOOD
                    newToy = new Doll(name, price, age, material);
                    break;
                default:
                    System.out.println("Помилка: невідомий тип іграшки '" + type + "'. Доступні: car, doll.");
                    return;
            }

            // Викликаємо сервіс для додавання
            manager.addToyToRoom(newToy);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Ціна має бути числом.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: Невірне значення Enum (вік, розмір або матеріал). Перевірте правильність написання.");
        } catch (Exception e) {
            System.out.println("Сталася помилка при додаванні: " + e.getMessage());
        }
    }
}