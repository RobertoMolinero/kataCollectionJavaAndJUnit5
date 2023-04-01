package kattis.problems99;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int oldPrice = in.nextInt();

        Problems99 problems99 = new Problems99();
        out.print(problems99.getNearest99(oldPrice));
        out.close();
    }
}
