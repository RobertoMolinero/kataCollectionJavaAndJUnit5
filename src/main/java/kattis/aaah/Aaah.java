package kattis.aaah;

public class Aaah {

    public String getCanGo(String jonMarius, String doctor) {
        return jonMarius.length() < doctor.length() ? "no" : "go";
    }
}
