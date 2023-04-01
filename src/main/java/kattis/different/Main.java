package kattis.different;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNext()) {
            BigInteger bigInteger0 = new BigInteger(in.next());
            BigInteger bigInteger1 = new BigInteger(in.next());
            System.out.println(bigInteger0.subtract(bigInteger1).abs());
        }

        in.close();
    }
}
