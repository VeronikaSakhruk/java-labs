package parcelplus.lab13.command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import parcelplus.Log;

public class OperationQueue {

    private final Queue<Command> pending = new ArrayDeque<>();
    private final Deque<Command> executed = new ArrayDeque<>();
    private final List<String> journal = new ArrayList<>();

    public synchronized void submit(Command command) {
        pending.add(command);
        Log.log("OperationQueue", "Queued: " + command.description() + " (pending " + pending.size() + ")");
    }

    public synchronized void processAll() {
        while (!pending.isEmpty()) {
            Command command = pending.poll();
            Log.log("OperationQueue", "Dispatching to " + command.getClass().getSimpleName()
                    + ": " + command.description() + " (still pending " + pending.size() + ")");
            command.execute();
            executed.push(command);
            journal.add("EXECUTED " + command.description());
        }
    }

    public synchronized void undoLast() {
        Command command = executed.poll();
        if (command == null) {
            Log.log("OperationQueue", "Nothing to undo");
            return;
        }
        Log.log("OperationQueue", "Undoing through " + command.getClass().getSimpleName()
                + ": " + command.description());
        command.undo();
        journal.add("UNDONE   " + command.description());
    }

    public synchronized void printJournal() {
        Log.log("OperationQueue", "Warehouse operation journal (" + journal.size() + " entries):");
        for (int i = 0; i < journal.size(); i++) {
            Log.log("OperationQueue", (i + 1) + ". " + journal.get(i));
        }
    }
}
