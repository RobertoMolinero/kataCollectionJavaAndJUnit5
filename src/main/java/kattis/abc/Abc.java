package kattis.abc;

public class Abc {

    private Integer acc;

    private Boolean stringMode;

    private final StringBuilder output;

    public Abc() {
        this.acc = 0;
        this.stringMode = Boolean.FALSE;
        this.output = new StringBuilder();
    }

    public Integer getAcc() {
        return acc;
    }

    public Boolean getStringMode() {
        return stringMode;
    }

    public StringBuilder getOutput() {
        return output;
    }

    public void interpret(String code) {

        for (int i = 0; i < code.length(); i++) {

            switch (code.charAt(i)) {
                case 'a' -> ++acc;
                case 'b' -> --acc;
                case 'c' -> output.append(stringMode ? Character.toString(acc) : acc);
                case 'd' -> acc *= -1;
                case 'r' -> acc = (int) Math.floor(Math.random() * (acc + 1));
                case 'n' -> acc = 0;
                case '$' -> stringMode = !stringMode;
                case 'l' -> i = -1;
                case ';' -> output.append(acc).append(" ").append(Character.toString(acc));
            }
        }
    }
}
