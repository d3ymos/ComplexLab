package command;

import service.GameRoomManager;

public class SaveRoomCommand implements Command {
    private final GameRoomManager manager;

    public SaveRoomCommand(GameRoomManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDesc() {
        return "Зберегти кімнату у файл. Приклад: save data.txt";
    }

    @Override
    public void execute(String params) {
        if (params == null || params.isEmpty()) {
            System.out.println("Будь ласка, вкажіть назву файлу (наприклад, data.txt)");
            return;
        }
        manager.save(params);
    }
}