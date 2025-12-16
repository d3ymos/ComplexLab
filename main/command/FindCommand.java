package command;

import service.GameRoomManager;

public class FindCommand implements Command {
    private final GameRoomManager manager;

    public FindCommand(GameRoomManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDesc() {
        return "Знайти іграшки у ціновому діапазоні. Приклад: find 100 300";
    }

    @Override
    public void execute(String params) {
        if (params == null || params.isEmpty()) {
            System.out.println("Будь ласка, вкажіть мінімальну та максимальну ціну.");
            return;
        }

        try {
            String[] parts = params.split(" ");
            if (parts.length < 2) {
                System.out.println("Помилка: потрібно вказати два числа (мін. та макс. ціну).");
                return;
            }

            double min = Double.parseDouble(parts[0]);
            double max = Double.parseDouble(parts[1]);

            if (min > max) {
                System.out.println("Помилка: мінімальна ціна не може бути більшою за максимальну.");
                return;
            }

            // Викликаємо метод менеджера
            manager.findAndShowToysByRange(min, max);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: параметри мають бути числами.");
        }
    }
}