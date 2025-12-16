

import command.*;
import model.GameRoom;
import service.GameRoomManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Головний клас меню, що керує введенням користувача та
 * викликає відповідні команди.
 */
public class ConsoleMenu {
    private final Map<String, Command> commands;
    private final Scanner scanner;
    private GameRoomManager manager; // Посилання на сервіс

    public ConsoleMenu() {
        this.commands = new HashMap<>();
        this.scanner = new Scanner(System.in);

        // 1. Ініціалізація даних (Кімната з бюджетом 1000)
        GameRoom room = new GameRoom(1000.0);

        // 2. Ініціалізація сервісу
        this.manager = new GameRoomManager(room);

        // 3. Наповнення меню командами
        initCommands();
    }

    /**
     * Реєструє команди та передає їм залежності (manager).
     */
    private void initCommands() {
        commands.put("add", new AddToyCommand(manager));
        commands.put("sort", new SortToysCommand(manager));
        commands.put("show", new ShowRoomCommand(manager));
        commands.put("save", new SaveRoomCommand(manager));
        commands.put("load", new LoadRoomCommand(manager));
        commands.put("find", new FindCommand(manager));
    }

    public void run() {
        System.out.println("Вас вітає програма 'Ігрова Кімната'!");
        System.out.println("Бюджет кімнати: " + manager.calculateTotalCost() + " / " + 1000.0);
        System.out.println("Введіть 'help' для довідки або 'exit' для виходу.");

        while (true) {
            System.out.print("\nВведіть команду > ");

            if (!scanner.hasNextLine()) break; // Захист від ctrl+d

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            // Розбиваємо на команду та параметри
            String[] parts = input.split(" ", 2);
            String commandKey = parts[0].toLowerCase();
            String params = (parts.length > 1) ? parts[1] : "";

            // Вбудовані команди
            if ("exit".equals(commandKey)) {
                System.out.println("Дякуємо! До побачення!");
                break;
            }

            if ("help".equals(commandKey)) {
                showHelp();
                continue;
            }

            // Пошук та виконання команди
            Command command = commands.get(commandKey);

            if (command != null) {
                command.execute(params);
            } else {
                System.out.println("Помилка: невідома команда '" + commandKey + "'.");
                System.out.println("Введіть 'help' для списку доступних команд.");
            }
        }
        scanner.close();
    }

    private void showHelp() {
        System.out.println("\n--- Довідка по командам ---");
        System.out.printf("  %-10s - %s\n", "exit", "Вийти з програми.");
        System.out.printf("  %-10s - %s\n", "help", "Показати цю довідку.");

        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.printf("  %-10s - %s\n", entry.getKey(), entry.getValue().getDesc());
        }
        System.out.println("---------------------------");
    }
}