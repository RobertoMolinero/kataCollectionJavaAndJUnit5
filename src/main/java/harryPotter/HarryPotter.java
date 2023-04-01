package harryPotter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HarryPotter {

    private static final Map<Integer, Double> discountMap = Map.of(
            0, 0.00,
            1, 0.00,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

    private static final int NUMBER_OF_BOOKS = 5;

    public double calculatePrice(List<Integer> basket) {
        List<Integer> sequenceLengths = calculateSequenceLengths(basket);
        double sum = 0;

        for (Integer sequence : sequenceLengths) {
            sum += 8 * sequence * (1 - discountMap.get(sequence));
        }

        return sum;
    }

    public List<Integer> calculateSequenceLengths(List<Integer> basket) {
        List<Integer> sequenceLengths = new ArrayList<>();

        for (int bookNumber = 0; bookNumber < NUMBER_OF_BOOKS; bookNumber++) {
            int finalBookNumber = bookNumber;
            int books = basket.stream().filter(b -> b == finalBookNumber).collect(Collectors.toList()).size();

            while (sequenceLengths.size() < books) {
                sequenceLengths.add(0);
            }

            orderSequenceByDiscountStep(sequenceLengths);

            for (int i = 0; i < books; i++) {
                Integer n = sequenceLengths.get(i) + 1;
                sequenceLengths.set(i, n);
            }
        }

        return sequenceLengths;
    }

    private void orderSequenceByDiscountStep(List<Integer> sequenceLengths) {
        Collections.sort(sequenceLengths, (o1, o2) -> {
            Map<Integer, Integer> discountStep = Map.of(
                    0, 0,
                    1, -5,
                    2, -5,
                    3, -10,
                    4, -5,
                    5, 0);

            if (discountStep.get(o1) < discountStep.get(o2)) {
                return -1;
            } else if (discountStep.get(o1) > discountStep.get(o2)) {
                return 1;
            }

            return 0;
        });
    }
}
