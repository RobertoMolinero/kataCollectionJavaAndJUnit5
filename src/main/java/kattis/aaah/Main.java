package kattis.aaah;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String jonMarius = in.next();
        String doctor = in.next();

        Aaah aaah = new Aaah();
        out.print(aaah.getCanGo(jonMarius, doctor));
        out.close();
    }
}
