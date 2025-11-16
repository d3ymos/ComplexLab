package command;

public class SaveRoomCommand implements Command {
    @Override
    public String getDesc() {
        return "Зберегти поточну кімнату у файл (наприклад: 'save my_room.txt')";
    }

    @Override
    public void execute(String params) {
        System.out.println("[ВИКОНУЄТЬСЯ КОМАНДА: Зберегти кімнату... (параметри: " + params + ")]");
    }
}