package esolang.brainfuckCommand;

import java.util.Map;
import java.util.Scanner;

public record Interpreter(Machine machine, Program program) {

    private final static Scanner scanner = new Scanner(System.in);

    private static final Map<String, Command> COMMAND_MAP;

    static {
        COMMAND_MAP = Map.of(
                "<", (machine, program) -> {
                    if (machine.getMemoryPointer() == 0)
                        machine.setMemoryPointer(machine.getMemory().length - 1);
                    else
                        machine.decrementMemoryPointer();
                },
                ">", (machine, program) -> {
                    if (machine.getMemoryPointer() == machine.getMemory().length - 1)
                        machine.setMemoryPointer(0);
                    else
                        machine.incrementMemoryPointer();
                },
                "-", (machine, program) -> machine.decrementMemoryAt(machine.getMemoryPointer()),
                "+", (machine, program) -> machine.incrementMemoryAt(machine.getMemoryPointer()),
                ".", (machine, program) -> machine.appendToOutputBuffer(Character.toString(machine.getMemory()[machine.getMemoryPointer()])),
                ",", (machine, program) -> machine.setMemoryAt(machine.getMemoryPointer(), (byte) (scanner.next().charAt(0))),
                "[", (machine, program) -> {
                    if (machine.getMemory()[machine.getMemoryPointer()] == 0) {
                        program.setCommandPointer(program.getOpenCloseAssignment().get(program.getCommandPointer()));
                    }
                },
                "]", (machine, program) -> {
                    if (machine.getMemory()[machine.getMemoryPointer()] != 0) {
                        program.setCommandPointer(program.getCloseOpenAssignment().get(program.getCommandPointer()));
                    }
                });
    }

    public void executeCommand(String commandType) {
        Command command = COMMAND_MAP.get(commandType);
        command.execute(this.machine, this.program);
    }

    public String interpret() {
        do {
            char charAt = program.getCommand().charAt(program.getCommandPointer());
            executeCommand(String.valueOf(charAt));
            program.incrementCommandPointer();
        } while (program.getCommandPointer() < program.getCommand().length());

        return machine.getOutputBuffer().toString();
    }
}
