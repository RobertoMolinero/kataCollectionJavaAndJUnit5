package esolang.brainfuck;

public class Main {

    public static void main(String[] args) {
        String helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";

        Brainfuck brainfuck = new Brainfuck();
        String interpret = brainfuck.interpret(helloWorld);
        System.out.println(interpret);
    }
}
