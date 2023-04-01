package esolang.brainfuckCommand;

import java.util.*;

public class Program {

    private final String command;
    private int commandPointer;

    private final Map<Integer, Integer> openCloseAssignment = new HashMap<>();
    private final Map<Integer, Integer> closeOpenAssignment = new HashMap<>();

    public Program(String command) {
        this(command, 0);
    }

    public Program(String command, int commandPointer) {
        this.command = reduceToInstructionSet(command);
        this.commandPointer = commandPointer;

        if (!validate(command)) {
            throw new IllegalArgumentException("The brackets within the statement are not balanced.");
        }
    }

    public Map<Integer, Integer> getOpenCloseAssignment() {
        return openCloseAssignment;
    }

    public Map<Integer, Integer> getCloseOpenAssignment() {
        return closeOpenAssignment;
    }

    public String getCommand() {
        return command;
    }

    public int getCommandPointer() {
        return commandPointer;
    }

    public void setCommandPointer(int commandPointer) {
        this.commandPointer = commandPointer;
    }

    public void incrementCommandPointer() {
        this.commandPointer++;
    }

    private String reduceToInstructionSet(String completeCommand) {
        List<Integer> instructionSet = List.of(
                (int) '+', (int) '-',
                (int) '.', (int) ',',
                (int) '<', (int) '>',
                (int) '[', (int) ']'
        );

        StringBuilder command = new StringBuilder();
        completeCommand
                .chars()
                .filter(instructionSet::contains)
                .forEach(c -> command.append(Character.toString(c)));

        return command.toString();
    }

    private boolean validate(String command) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < command.length(); i++) {
            char charAt = command.charAt(i);

            if (charAt == '[') {
                stack.push(i);
            } else if (charAt == ']') {
                Integer poll = stack.poll();
                if (poll == null) {
                    return false;
                }
                openCloseAssignment.put(poll, i);
                closeOpenAssignment.put(i, poll);
            }
        }

        return stack.isEmpty();
    }
}
