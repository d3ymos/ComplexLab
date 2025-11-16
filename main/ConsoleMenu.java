import command.AddToyCommand;
import command.Command;
import command.SaveRoomCommand;
import command.SortToysCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {
    private final Map<String, Command> commands;
    private final Scanner scanner;

    public ConsoleMenu() {
        this.commands = new HashMap<>();
        this.scanner = new Scanner(System.in);
        initCommands();
    }

    private void initCommands() {
        commands.put("add", new AddToyCommand());
        commands.put("sort", new SortToysCommand());
        commands.put("save", new SaveRoomCommand());
    }

    public void run() {
        System.out.println("Вас вітає програма 'Ігрова Кімната'!");
        System.out.println("Введіть 'help' для довідки або 'exit' для виходу.");

        while (true) {
            System.out.print("\nВведіть команду > ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split(" ", 2);
            String commandKey = parts[0].toLowerCase(); // Ключ команди
            String params = (parts.length > 1) ? parts[1] : ""; // Параметри (якщо є)

            if ("exit".equals(commandKey)) {
                System.out.println("Дякуємо! До побачення!");
                break;
            }

            if ("help".equals(commandKey)) {
                showHelp();
                continue;
            }

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
        System.out.println("--- Довідка по командам ---");
        System.out.println("  help - Показати цю довідку.");
        System.out.println("  exit - Вийти з програми.");

        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.printf("  %-10s - %s\n", entry.getKey(), entry.getValue().getDesc());
        }
        System.out.println("---------------------------");
    }
}