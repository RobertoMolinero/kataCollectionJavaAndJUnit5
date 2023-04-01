package kattis.addingwords;

import java.util.HashMap;
import java.util.Map;

public class AddingWords {

    private Map<String, Integer> variables = new HashMap<>();

    public AddingWords() {
    }

    public AddingWords(Map<String, Integer> variables) {
        this.variables = variables;
    }

    public Map<String, Integer> getVariables() {
        return variables;
    }

    public void evaluateExpression(String expression) {
        String[] split = expression.split(" ");

        if (split[0].equals("clear")) {
            clearVariables();
        } else if (split[0].equals("def")) {
            addVariable(split[1], Integer.parseInt(split[2]));
        }
    }

    private void addVariable(String variable, Integer number) {
        variables.put(variable, number);
    }

    private void clearVariables() {
        variables.clear();
    }
}
