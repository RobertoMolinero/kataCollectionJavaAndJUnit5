package esolang.brainfuckCommand;

public interface Command {

    /**
     * This is a list of all commands that must be implemented by a valid Brainfuck interpreter.
     *
     * > = increments the pointer (tape)
     * < = decrements the pointer (tape)
     * + = increments the current cell value
     * − = decrements the current cell value
     * . = outputs the current cell value as ASCII character on the standard output
     * , = reads a character from standard input and stores its ASCII value in the current cell
     * [ = jumps to the front, behind the matching ']' command, if the current cell value is 0
     * ] = jumps back, behind the matching '[' command, if the current cell value is not 0
     *
     * @param machine The memory tape and a pointer to the position at which work can be done.
     * @param program The program, consisting of a string and a pointer to the current position.
     */
    void execute(Machine machine, Program program);
}
