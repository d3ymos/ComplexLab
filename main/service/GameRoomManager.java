package service;

import model.GameRoom;
import model.Toy;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервісний клас, який містить основну бізнес-логіку програми.
 * Він керує ігровою кімнатою, виконує обчислення, сортування та
 * делегує роботу з файлами класу FileService.
 */
public class GameRoomManager {
    private final GameRoom gameRoom;
    private final FileService fileService;

    public GameRoomManager(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
        this.fileService = new FileService(); // Створюємо екземпляр сервісу файлів
    }

    /**
     * Додає іграшку до кімнати, якщо це дозволяє бюджет.
     */
    public boolean addToyToRoom(Toy toy) {
        double currentCost = calculateTotalCost();
        if (currentCost + toy.getPrice() <= gameRoom.getBudget()) {
            gameRoom.addToy(toy);
            System.out.println("Успішно додано: " + toy.getName());
            return true;
        } else {
            System.out.printf("Помилка: Недостатньо бюджету! (Ціна: %.2f, Залишок: %.2f)\n",
                    toy.getPrice(), (gameRoom.getBudget() - currentCost));
            return false;
        }
    }

    /**
     * Рахує сумарну вартість всіх іграшок.
     */
    public double calculateTotalCost() {
        return gameRoom.getToys().stream()
                .mapToDouble(Toy::getPrice)
                .sum();
    }

    /**
     * Сортує іграшки за ціною (від меншої до більшої).
     */
    public void sortByPrice() {
        gameRoom.getToys().sort(Comparator.comparingDouble(Toy::getPrice));
        System.out.println("Іграшки відсортовано за ціною.");
    }

    /**
     * Сортує іграшки за назвою (алфавітний порядок).
     */
    public void sortByName() {
        gameRoom.getToys().sort(Comparator.comparing(Toy::getName));
        System.out.println("Іграшки відсортовано за назвою.");
    }

    /**
     * Знаходить та виводить іграшки у заданому ціновому діапазоні.
     */
    public void findAndShowToysByRange(double min, double max) {
        List<Toy> foundToys = gameRoom.getToys().stream()
                .filter(t -> t.getPrice() >= min && t.getPrice() <= max)
                .collect(Collectors.toList());

        System.out.println("\n--- Результати пошуку (Ціна: " + min + " - " + max + ") ---");
        if (foundToys.isEmpty()) {
            System.out.println("Іграшок не знайдено.");
        } else {
            for (Toy toy : foundToys) {
                System.out.println(toy);
            }
        }
        System.out.println("------------------------------------------------");
    }

    /**
     * Виводить поточний стан кімнати (звіт).
     */
    public void printInventory() {
        System.out.println("\n--- Вміст Ігрової Кімнати ---");
        if (gameRoom.getToys().isEmpty()) {
            System.out.println("Кімната порожня.");
        } else {
            for (Toy toy : gameRoom.getToys()) {
                System.out.println(toy);
            }
        }
        double total = calculateTotalCost();
        System.out.printf("Всього іграшок: %d | Витрачено: %.2f / Бюджет: %.2f\n",
                gameRoom.getToys().size(), total, gameRoom.getBudget());
        System.out.println("-----------------------------");
    }

    // --- Методи для роботи з файлами ---

    /**
     * Зберігає поточний стан кімнати у файл через FileService.
     */
    public void save(String filename) {
        try {
            fileService.saveRoom(gameRoom, filename);
            System.out.println("Кімнату успішно збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні файлу: " + e.getMessage());
        }
    }

    /**
     * Завантажує стан кімнати з файлу через FileService.
     */
    public void load(String filename) {
        try {
            List<Toy> loadedToys = fileService.loadRoom(filename);
            // Перезаписуємо список іграшок у кімнаті
            gameRoom.setToys(loadedToys);
            System.out.println("Кімнату завантажено. Кількість іграшок: " + loadedToys.size());
            // Одразу показуємо, що завантажили
            printInventory();
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні файлу: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Помилка: Файл пошкоджений або має неправильний формат.");
        }
    }
}