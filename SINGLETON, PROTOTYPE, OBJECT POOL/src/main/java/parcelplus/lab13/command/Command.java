package parcelplus.lab13.command;

public interface Command {

    void execute();

    void undo();

    String description();
}
