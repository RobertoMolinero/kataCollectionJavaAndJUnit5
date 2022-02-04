package euler.p008;

public class LargestProductInASeries {

    private LargestProductInASeries() {
    }

    public static long findLargestProductInASeries(String sequence) {

        long largestProduct = 1;

        for (var i = 0; i < sequence.length() - 12; i++) {
            var substring = sequence.substring(i, i + 13);

            if (substring.contains("0")) {
                int lastIndexOf = substring.lastIndexOf("0");
                i += lastIndexOf;
                continue;
            }

            long productOfSequence = getProductOfSequence(substring);
            if (productOfSequence > largestProduct) {
                largestProduct = productOfSequence;
            }
        }

        return largestProduct;
    }

    public static long getProductOfSequence(String sequence) {

        long productOfSequence = 1;

        for (var i = 0; i < sequence.length(); i++) {
            var substring = sequence.substring(i, i + 1);
            var parseInt = Integer.parseInt(substring);
            productOfSequence *= parseInt;
        }

        return productOfSequence;
    }
}
