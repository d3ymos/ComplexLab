package command;

public class SortToysCommand implements Command {
    @Override
    public String getDesc() {
        return "Сортувати іграшки (наприклад: 'sort by_price')";
    }

    @Override
    public void execute(String params) {
        System.out.println("[ВИКОНУЄТЬСЯ КОМАНДА: Сортувати іграшки... (параметри: " + params + ")]");
    }
}