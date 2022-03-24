
import java.util.ArrayList;
import java.util.List;

public class SorterHelper {

//    Sorter<String> stringSorterPerItem;
//    Sorter<String> stringSorterUntilNoChange;
//    Sorter<String> stringSorterWhileNeeded;
//
//    Sorter<Integer> integerSorterPerItem;
//    Sorter<Integer> integerSorterUntilNoChange;
//    Sorter<Integer> integerSorterWhileNeeded;
//
//    Sorter<Double> doubleSorterPerItem;
//    Sorter<Double> doubleSorterUntilNoChange;
//    Sorter<Double> doubleSorterWhileNeeded;

    public SorterHelper() {
        // Pass
//        stringSorterPerItem = new BubbleSortPassPerItem<>();
//        stringSorterUntilNoChange = new BubbleSortUntilNoChange<>();
//        stringSorterWhileNeeded = new BubbleSortWhileNeeded<>();
//
//        integerSorterPerItem = new BubbleSortPassPerItem<>();
//        integerSorterUntilNoChange = new BubbleSortUntilNoChange<>();
//        integerSorterWhileNeeded = new BubbleSortWhileNeeded<>();
//
//        doubleSorterPerItem = new BubbleSortPassPerItem<>();
//        doubleSorterUntilNoChange = new BubbleSortUntilNoChange<>();
//        doubleSorterWhileNeeded = new BubbleSortWhileNeeded<>();
    }

    public String getAlphaNumericString(int n) // https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    {

        // chose a Character random from this String
        var alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        var sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            var index
                    = (int)(alphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    private String[] listToStringArray(List<String> stringArray) {
        String[] s = new String[stringArray.size()];
        s = stringArray.toArray(s);
        return s;
    }


    public String[] getStringArrayOfDesiredLength(int size, double desiredArraySize) {
        var stringArray = new ArrayList<String>();
        for (var i = 0; i < desiredArraySize; i++) {
            stringArray.add(getAlphaNumericString(size));
        }
        return listToStringArray(stringArray);
    }

    public String[] returnStirngArrayCopy(String[] stringArray) {
        var s = new String[stringArray.length];
        System.arraycopy(stringArray, 0, s, 0, stringArray.length);
        return s;
    }

    public Double[] returnDoubleArrayCopy(Double[] longArray) {
        var s = new Double[longArray.length];
        System.arraycopy(longArray, 0, s, 0, longArray.length);
        return s;
    }

    public Integer[] returnIntegerArrayCopy(Integer [] integerArray) {
        var s = new Integer[integerArray.length];
        System.arraycopy(integerArray, 0, s, 0, integerArray.length);
        return s;
    }

    public List<Long> checkStringArrayPerformance(int iterations, Sorter<String> sorter, String[] stringArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnStirngArrayCopy(stringArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            timeList.add(endTime - startTime);
        }
        return timeList;
    }

    public List<Long> checkDoubleArrayPerformance(int iterations, Sorter<Double> sorter, Double[] longArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnDoubleArrayCopy(longArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            timeList.add(endTime - startTime);
        }
        return timeList;
    }

    public List<Long> checkIntegerArrayPerformance(int iterations, Sorter<Integer> sorter, Integer[] integerArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnIntegerArrayCopy(integerArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            timeList.add(endTime - startTime);
        }
        return timeList;
    }

}
