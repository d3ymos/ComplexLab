package command;

import service.GameRoomManager;

public class LoadRoomCommand implements Command {
    private final GameRoomManager manager;

    public LoadRoomCommand(GameRoomManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDesc() {
        return "Завантажити кімнату з файлу. Приклад: load data.txt";
    }

    @Override
    public void execute(String params) {
        if (params == null || params.isEmpty()) {
            System.out.println("Будь ласка, вкажіть назву файлу.");
            return;
        }
        manager.load(params);
    }
}