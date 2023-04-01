package kattis.problems99;

public class Problems99 {

    public int getNearest99(int oldPrice) {

        if (oldPrice < 99) {
            return 99;
        }

        int mod100 = oldPrice % 100;

        if (mod100 < 49) {
            return oldPrice - (mod100 + 1);
        }

        return oldPrice + (99 - mod100);
    }
}
