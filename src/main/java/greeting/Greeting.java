package greeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Greeting {

    public static final String GREETING_FORMULA = "Hello, %s.";
    public static final String DEFAULT_NAME = "my friend";

    public static final String GREETING_START = "Hello, ";
    public static final String GREETING_SEPARATOR = ", ";
    public static final String GREETING_END = " and %s.";

    public static final String SHOUTING_FORMULA_FIRST = "HELLO %s!";
    public static final String SHOUTING_FORMULA = "AND HELLO %s!";

    public static String greet(List<String> names) {
        if (names == null || names.size() == 0)
            return String.format(GREETING_FORMULA, DEFAULT_NAME);

        List<String> splittedNames = new ArrayList<>();

        for (String n : names) {
            if (n.contains("\""))
                splittedNames.add(n.replaceAll("\"", ""));
            else
                Collections.addAll(splittedNames, n.split(", "));
        }

        Map<Boolean, List<String>> isUppercase = splittedNames.stream().collect(Collectors.groupingBy(name -> name.toUpperCase().equals(name)));

        String multipleGreetings = multipleGreetings(isUppercase.get(Boolean.FALSE));
        String multipleShoutings = multipleShoutings((multipleGreetings.length() == 0), isUppercase.get(Boolean.TRUE));

        return (multipleGreetings + " " + multipleShoutings).trim();
    }

    private static String multipleGreetings(List<String> names) {
        if (names == null) return "";
        if (names.size() == 1) return String.format(GREETING_FORMULA, names.get(0));

        ArrayList<String> listOfNames = new ArrayList<>(names);

        String lastElement = listOfNames.remove(listOfNames.size() - 1);
        String end = String.format(GREETING_END, lastElement);

        return String.format(GREETING_START + String.join(GREETING_SEPARATOR, listOfNames) + end);
    }

    private static String multipleShoutings(boolean shoutingAtStart, List<String> names) {
        if (names == null) return "";
        if (names.size() == 1)
            return String.format(shoutingAtStart ? SHOUTING_FORMULA_FIRST : SHOUTING_FORMULA, names.get(0));

        List<String> shoutingList = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);

            if (shoutingAtStart && i == 0) {
                shoutingList.add(String.format(SHOUTING_FORMULA_FIRST, name));
            } else {
                shoutingList.add(String.format(SHOUTING_FORMULA, name));
            }
        }

        return String.join(" ", shoutingList);
    }
}
