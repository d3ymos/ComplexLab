package command;

import service.GameRoomManager;

public class SortToysCommand implements Command {
    private final GameRoomManager manager;

    public SortToysCommand(GameRoomManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDesc() {
        return "Сортувати іграшки. Параметри: price, name.";
    }

    @Override
    public void execute(String params) {
        if (params == null) {
            System.out.println("Вкажіть параметр сортування (price або name).");
            return;
        }

        switch (params.trim().toLowerCase()) {
            case "price":
                manager.sortByPrice();
                // Одразу покажемо результат
                manager.printInventory();
                break;
            case "name":
                manager.sortByName();
                manager.printInventory();
                break;
            default:
                System.out.println("Невідомий параметр сортування. Використовуйте 'price' або 'name'.");
        }
    }
}