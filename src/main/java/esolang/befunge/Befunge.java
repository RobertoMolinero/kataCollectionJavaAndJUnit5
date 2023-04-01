package esolang.befunge;

import java.nio.charset.StandardCharsets;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

public class Befunge {

    Map<Position, String> programMap;

    Pointer pointer;

    Deque<String> arguments;

    Deque<Integer> stack;

    StringBuilder output;

    int AREA_MAX_X = 79;
    int AREA_MAX_Y = 24;

    boolean inputMode = false;

    public Befunge() {
    }

    public Map<Position, String> init(String program) {
        return init(program, new ArrayDeque<>());
    }

    public Map<Position, String> init(String program, Deque<String> arguments) {
        this.programMap = new HashMap<>();

        CharacterIterator iterator = new StringCharacterIterator(program);

        int mapPositionX = 0;
        int mapPositionY = 0;

        while (iterator.current() != CharacterIterator.DONE) {
            char current = iterator.current();

            if (current == '\n') {
                mapPositionY++;
                mapPositionX = 0;
            } else {
                programMap.put(new Position(mapPositionX, mapPositionY), "" + current);
                mapPositionX++;
            }

            iterator.next();
        }

        this.pointer = new Pointer(new Position(0, 0), Direction.EAST);
        this.arguments = arguments;
        this.stack = new ArrayDeque<>();
        this.output = new StringBuilder();

        return this.programMap;
    }

    void interpret() {
        for (; ; ) {
            interpretNext();

            String nextCommand = programMap.get(pointer.position());
            if (nextCommand == null) continue;
            if (nextCommand.equals("@")) break;
        }
    }

    void interpret(int steps) {
        for (int i = 0; i < steps; i++) {
            interpretNext();

            String nextCommand = programMap.get(pointer.position());
            if (nextCommand == null) continue;
            if (nextCommand.equals("@")) break;
        }
    }

    void interpretNext() {
        String nextCommand = programMap.get(pointer.position());
        Direction direction = this.pointer.direction();

        if (nextCommand != null) {
            if (inputMode && !nextCommand.equals("\"")) {
                pushValueAsCharacter(nextCommand);
            } else {
                switch (nextCommand) {
                    case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> pushValue(nextCommand);
                    case "v", "<", ">", "^", "_", "|", "?" -> direction = navigate(nextCommand);
                    case "+", "-", "*", "/", "%", "`" -> applyDualMathematicalOperator(nextCommand);
                    case "&", "~" -> inputCharacter(nextCommand);
                    case "#" -> this.pointer = new Pointer(movePosition(direction), direction);
                    case "!" -> not();
                    case "\"" -> inputMode = !inputMode;
                    case ":" -> duplicate();
                    case "\\" -> swap();
                    case "$" -> poll();
                    case "g" -> readArea();
                    case "p" -> modifyArea();
                    case "." -> output();
                    case "," -> outputValueAsCharacter();
                    default -> {
                    }
                }
            }
        }

        this.pointer = new Pointer(movePosition(direction), direction);
    }

    private void inputCharacter(String nextCommand) {
        String poll = this.arguments.poll();

        if ("&".equals(nextCommand)) {
            int pollAsNumber;

            try {
                pollAsNumber = Integer.parseInt(poll);
            } catch (NumberFormatException e) {
                pollAsNumber = 0;
            }

            this.stack.push(pollAsNumber);
        } else if ("~".equals(nextCommand)) {
            if (poll == null) {
                this.stack.push(Integer.valueOf("-1"));
            } else {
                this.stack.push((int) poll.charAt(0));
            }
        }
    }

    private void readArea() {
        Integer y = this.stack.poll();
        Integer x = this.stack.poll();
        Position position = new Position(x, y);

        String codeAtPosition = this.programMap.get(position);

        if (codeAtPosition != null) {
            this.stack.push((int) codeAtPosition.charAt(0));
        } else {
            this.stack.push(Integer.valueOf("32"));
        }
    }

    private void modifyArea() {
        Integer y = this.stack.poll();
        Integer x = this.stack.poll();
        Integer value = this.stack.poll();
        Position position = new Position(x, y);

        this.programMap.replace(position, Character.toString(value));
    }

    private Direction navigate(String nextCommand) {
        if (List.of("v", "<", ">", "^").contains(nextCommand)) return Direction.getDirection(nextCommand);

        if ("_".equals(nextCommand)) {
            Integer poll = this.stack.poll();
            return Integer.valueOf("0").equals(poll) ? Direction.EAST : Direction.WEST;
        }

        if ("|".equals(nextCommand)) {
            Integer poll = this.stack.poll();
            return Integer.valueOf("0").equals(poll) ? Direction.SOUTH : Direction.NORTH;
        }

        if ("?".equals(nextCommand)) {
            return Direction.getRandomDirection();
        }

        return Direction.getDirection(nextCommand);
    }

    private void poll() {
        this.stack.poll();
    }

    private void swap() {
        Integer last = this.stack.poll();
        Integer secondToLast = this.stack.poll();

        this.stack.push(last);
        this.stack.push(secondToLast);
    }

    private void duplicate() {
        Integer value = this.stack.peek();

        if (value == null) {
            this.stack.push(Integer.valueOf("0"));
            this.stack.push(Integer.valueOf("0"));
        } else {
            this.stack.push(value);
        }
    }

    private void output() {
        this.output.append(this.stack.poll() + " ");
    }

    private void outputValueAsCharacter() {
        this.output.append(Character.toString(this.stack.poll()));
    }

    private void applyDualMathematicalOperator(String nextCommand) {
        Integer pollOfB = this.stack.poll();
        int b = pollOfB != null ? pollOfB : 0;

        Integer pollOfA = this.stack.poll();
        int a = pollOfA != null ? pollOfA : 0;

        switch (nextCommand) {
            case "+" -> stack.push((int) (a + b));
            case "-" -> stack.push((int) (a - b));
            case "*" -> stack.push((int) (a * b));
            case "/" -> stack.push((int) (a / b));
            case "%" -> stack.push((int) (a % b));
            case "`" -> stack.push(a > b ? Integer.valueOf("1") : Integer.valueOf("0"));
            default -> throw new IllegalArgumentException("Unknown dual operator!");
        }
    }

    private void not() {
        Integer poll = this.stack.poll();
        stack.push(poll == 0 ? Integer.valueOf("1") : Integer.valueOf("0"));
    }

    private Position movePosition(Direction direction) {
        int x = this.pointer.position().x() + direction.getHorizontal();
        if (x < 0) x = AREA_MAX_X;
        if (x > AREA_MAX_X) x = 0;

        int y = this.pointer.position().y() + direction.getVertical();
        if (y < 0) y = AREA_MAX_Y;
        if (y > AREA_MAX_Y) y = 0;

        return new Position(x, y);
    }

    private void pushValue(String nextCommand) {
        this.stack.push(Integer.decode(nextCommand));
    }

    private void pushValueAsCharacter(String nextCommand) {
        this.stack.push((int) nextCommand.charAt(0));
    }
}
