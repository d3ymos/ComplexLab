package command;

public class AddToyCommand implements Command {
    @Override
    public String getDesc() {
        return "Додати нову іграшку до кімнати";
    }

    @Override
    public void execute(String params) {
        System.out.println("[ВИКОНУЄТЬСЯ КОМАНДА: Додати іграшку... (параметри: " + params + ")]");
    }
}