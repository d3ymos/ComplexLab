package command;

public interface Command {
    String getDesc();
    void execute(String params);
}