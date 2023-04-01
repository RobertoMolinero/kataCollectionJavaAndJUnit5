package esolang.befunge;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {


    public static void main(String[] args) {

        Deque<String> test = new ArrayDeque<>();

        test.push("1");
        test.push("2");
        test.push("3");

        test.push(test.peek());

        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());


        Byte b = "H".getBytes()[0];

        System.out.println("" + b);

    }
}
