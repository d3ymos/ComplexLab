package command;

import service.GameRoomManager;

public class ShowRoomCommand implements Command {
    private final GameRoomManager manager;

    public ShowRoomCommand(GameRoomManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDesc() {
        return "Показати список іграшок у кімнаті.";
    }

    @Override
    public void execute(String params) {
        manager.printInventory();
    }
}