package esolang.brainfuck;

import java.util.*;

public class Brainfuck {

    private final byte[] memory;
    private int memoryPointer;

    private final StringBuilder outputBuffer = new StringBuilder();

    private final Map<Integer, Integer> openCloseAssignment = new HashMap<>();
    private final Map<Integer, Integer> closeOpenAssignment = new HashMap<>();

    private final static Scanner scanner = new Scanner(System.in);

    private static final int DEFAULT_MEMORY_SIZE = 32;

    public int getMemoryPointer() {
        return memoryPointer;
    }

    public byte[] getMemory() {
        return memory;
    }

    public StringBuilder getOutputBuffer() {
        return outputBuffer;
    }

    public Map<Integer, Integer> getOpenCloseAssignment() {
        return openCloseAssignment;
    }

    public Map<Integer, Integer> getCloseOpenAssignment() {
        return closeOpenAssignment;
    }

    public Brainfuck() {
        memory = new byte[DEFAULT_MEMORY_SIZE];
    }

    public Brainfuck(int memoryPointer, byte[] memory) {
        if (memory.length == 0) {
            throw new IllegalArgumentException("Memory is empty.");
        }

        if (memoryPointer < 0 || memoryPointer >= memory.length) {
            throw new IllegalArgumentException("Pointer position out of range.");
        }

        this.memoryPointer = memoryPointer;
        this.memory = memory;
    }

    boolean validate(String command) {
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

    String interpret(String command) {
        if (!validate(command)) {
            throw new IllegalArgumentException("The brackets within the statement are not balanced.");
        }

        for (int i = 0; i < command.length(); i++) {
            char charAt = command.charAt(i);
            switch (charAt) {
                case '>':
                    incrementMemoryPointer();
                    break;
                case '<':
                    decrementMemoryPointer();
                    break;
                case '+':
                    incrementMemoryValue();
                    break;
                case '-':
                    decrementMemoryValue();
                    break;
                case '.':
                    output();
                    break;
                case ',':
                    input();
                    break;
                case '[':
                    i = startLoop(i);
                    break;
                case ']':
                    i = endLoop(i);
                    break;
                default:
                    break;
            }
        }

        return outputBuffer.toString();
    }

    void decrementMemoryPointer() {
        if (memoryPointer == 0) {
            memoryPointer = memory.length - 1;
        } else {
            memoryPointer--;
        }
    }

    void incrementMemoryPointer() {
        if (memoryPointer == memory.length - 1) {
            memoryPointer = 0;
        } else {
            memoryPointer++;
        }
    }

    void decrementMemoryValue() {
        memory[memoryPointer]--;
    }

    void incrementMemoryValue() {
        memory[memoryPointer]++;
    }

    void output() {
        outputBuffer.append(Character.toString(memory[memoryPointer]));
    }

    void input() {
        memory[memoryPointer] = (byte) (scanner.next().charAt(0));
    }

    int startLoop(int commandPointer) {
        if (memory[memoryPointer] == 0) {
            return openCloseAssignment.get(commandPointer);
        }
        return commandPointer;
    }

    int endLoop(int commandPointer) {
        if (memory[memoryPointer] != 0) {
            return closeOpenAssignment.get(commandPointer);
        }
        return commandPointer;
    }
}
