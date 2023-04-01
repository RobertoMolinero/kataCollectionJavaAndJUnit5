package esolang.brainfuckCommand;

public class Machine {

    private final byte[] memory;
    private int memoryPointer;
    private final StringBuilder outputBuffer = new StringBuilder();

    public Machine(byte[] memory, int memoryPointer) {
        if (memory.length == 0) {
            throw new IllegalArgumentException("Memory is empty.");
        }

        if (memoryPointer < 0 || memoryPointer >= memory.length) {
            throw new IllegalArgumentException("Pointer position out of range.");
        }

        this.memoryPointer = memoryPointer;
        this.memory = memory;
    }

    public int getMemoryPointer() {
        return memoryPointer;
    }

    public void setMemoryPointer(int memoryPointer) {
        this.memoryPointer = memoryPointer;
    }

    public void decrementMemoryPointer() {
        memoryPointer--;
    }

    public void incrementMemoryPointer() {
        memoryPointer++;
    }

    public byte[] getMemory() {
        return memory;
    }

    public void setMemoryAt(int memoryPointer, byte value) {
        this.memory[memoryPointer] = value;
    }

    public void decrementMemoryAt(int memoryPointer) {
        memory[memoryPointer]--;
    }

    public void incrementMemoryAt(int memoryPointer) {
        memory[memoryPointer]++;
    }

    public StringBuilder getOutputBuffer() {
        return outputBuffer;
    }

    public void appendToOutputBuffer(String value) {
        outputBuffer.append(value);
    }
}
