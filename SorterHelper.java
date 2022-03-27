
import java.util.*;
import java.util.stream.Collectors;

public class SorterHelper {

    Sorter<String> stringSorterPerItem;
    Sorter<String> stringSorterUntilNoChange;
    Sorter<String> stringSorterWhileNeeded;

    Sorter<Integer> integerSorterPerItem;
    Sorter<Integer> integerSorterUntilNoChange;
    Sorter<Integer> integerSorterWhileNeeded;

    Sorter<Double> doubleSorterPerItem;
    Sorter<Double> doubleSorterUntilNoChange;
    Sorter<Double> doubleSorterWhileNeeded;
    Random randomGenerator;

    public static int ITERATIONS  = 500;
    public static int WARMUP = 100;


    public SorterHelper() {
        // Pass
        randomGenerator = new Random();
        stringSorterPerItem = new BubbleSortPassPerItem<>();
        stringSorterUntilNoChange = new BubbleSortUntilNoChange<>();
        stringSorterWhileNeeded = new BubbleSortWhileNeeded<>();

        integerSorterPerItem = new BubbleSortPassPerItem<>();
        integerSorterUntilNoChange = new BubbleSortUntilNoChange<>();
        integerSorterWhileNeeded = new BubbleSortWhileNeeded<>();

        doubleSorterPerItem = new BubbleSortPassPerItem<>();
        doubleSorterUntilNoChange = new BubbleSortUntilNoChange<>();
        doubleSorterWhileNeeded = new BubbleSortWhileNeeded<>();
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

    public String[] returnStringArrayCopy(String[] stringArray) {
        var s = new String[stringArray.length];
        System.arraycopy(stringArray, 0, s, 0, stringArray.length);
        return s;
    }

    public Double[] returnDoubleArrayCopy(Double[] doubleArray) {
        var s = new Double[doubleArray.length];
        System.arraycopy(doubleArray, 0, s, 0, doubleArray.length);
        return s;
    }

    public Integer[] returnIntegerArrayCopy(Integer [] integerArray) {
        var s = new Integer[integerArray.length];
        System.arraycopy(integerArray, 0, s, 0, integerArray.length);
        return s;
    }

    public Long checkStringArrayPerformance(int iterations, Sorter<String> sorter, String[] stringArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnStringArrayCopy(stringArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            if (i > WARMUP)
                timeList.add(endTime - startTime);
        }
        return timeList.stream().reduce((long)0, (i, j)-> (i + j)/iterations);
    }

    public Long checkDoubleArrayPerformance(int iterations, Sorter<Double> sorter, Double[] doubleArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnDoubleArrayCopy(doubleArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            if (i > WARMUP)
                timeList.add(endTime - startTime);
        }
        return timeList.stream().reduce((long)0, (i, j)-> (i + j)/iterations);
    }

    public Long checkIntegerArrayPerformance(int iterations, Sorter<Integer> sorter, Integer[] integerArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnIntegerArrayCopy(integerArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            if (i > WARMUP)
                timeList.add(endTime - startTime);
        }
        return timeList.stream().reduce((long)0, (i, j)-> (i + j)/iterations);
    }

    public Double[] worstCaseDoubleList(int size) {
        ArrayList<Double> doubleArray = randomGenerator.doubles(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Double[doubleArray.size()];
        doubleArray.sort(Collections.reverseOrder());
        s = doubleArray.toArray(s);
        return s;
    }

    public Double[] bestCaseDoubleList(int size) {
        ArrayList<Double> doubleArray = randomGenerator.doubles(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Double[doubleArray.size()];
        Collections.sort(doubleArray);
        s = doubleArray.toArray(s);
        return s;
    }

    public Integer[] worstCaseIntegerList(int size) {
        ArrayList<Integer> integerArray = randomGenerator.ints(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Integer[integerArray.size()];
        integerArray.sort(Collections.reverseOrder());
        s = integerArray.toArray(s);
        return s;
    }

    public Integer[] bestCaseIntegerList(int size) {
        ArrayList<Integer> integerArray = randomGenerator.ints(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Integer[integerArray.size()];
        Collections.sort(integerArray);
        s = integerArray.toArray(s);
        return s;
    }

    public Double[] randomCaseDoubleList(int size) {
        ArrayList<Double> doubleArray = randomGenerator.doubles(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Double[doubleArray.size()];
        s = doubleArray.toArray(s);
        return s;
    }

    public Integer[] randomCaseIntegerList(int size) {
        ArrayList<Integer> integerArray = randomGenerator.ints(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Integer[integerArray.size()];
        s = integerArray.toArray(s);
        return s;
    }

    public Performance checkStringRandomCasePerformanceWithAllSorters(int size, int stringSize){
        var stringArrayOne = getStringArrayOfDesiredLength(stringSize, size);
        var performancePerItem = checkStringArrayPerformance(ITERATIONS, stringSorterPerItem, stringArrayOne);
        var performanceUntilNoChange = checkStringArrayPerformance(ITERATIONS, stringSorterUntilNoChange, stringArrayOne);
        var performanceWhileNeeded = checkStringArrayPerformance(ITERATIONS, stringSorterWhileNeeded, stringArrayOne);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, size, Type.STRING);
    }

    public Performance checkStringWorstCasePerformanceWithAllSorters(int size, int stringSize){
        var stringArrayOne = getStringArrayOfDesiredLength(stringSize, size);
        var a = new ArrayList<>(Arrays.asList(stringArrayOne));
        a.sort(Collections.reverseOrder());
        stringArrayOne = a.toArray(stringArrayOne);
        var performancePerItem = checkStringArrayPerformance(ITERATIONS, stringSorterPerItem, stringArrayOne);
        var performanceUntilNoChange = checkStringArrayPerformance(ITERATIONS, stringSorterUntilNoChange, stringArrayOne);
        var performanceWhileNeeded = checkStringArrayPerformance(ITERATIONS, stringSorterWhileNeeded, stringArrayOne);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, size, Type.STRING);
    }

    public Performance checkStringBestCasePerformanceWithAllSorters(int size, int stringSize){
        var stringArrayOne = getStringArrayOfDesiredLength(stringSize, size);
        var a = new ArrayList<>(Arrays.asList(stringArrayOne));
        Collections.sort(a);
        stringArrayOne = a.toArray(stringArrayOne);
        var performancePerItem = checkStringArrayPerformance(ITERATIONS, stringSorterPerItem, stringArrayOne);
        var performanceUntilNoChange = checkStringArrayPerformance(ITERATIONS, stringSorterUntilNoChange, stringArrayOne);
        var performanceWhileNeeded = checkStringArrayPerformance(ITERATIONS, stringSorterWhileNeeded, stringArrayOne);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, size, Type.STRING);
    }


    public Performance checkIntegerWorstCasePerformance(int arraySize) {
        var integerArray = worstCaseIntegerList(arraySize);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, integerArray);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, integerArray);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, integerArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.INTEGER);
    }

    public Performance checkIntegerBestCasePerformance(int arraySize) {
        var integerArray = bestCaseIntegerList(arraySize);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, integerArray);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, integerArray);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, integerArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.INTEGER);
    }

    public Performance checkIntegerRandomCasePerformance(int arraySize) {
        var integerArray = randomCaseIntegerList(arraySize);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, integerArray);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, integerArray);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, integerArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.INTEGER);
    }

    public Performance checkDoubleWorstCasePerformance(int arraySize) {
        var doubleArray = worstCaseDoubleList(arraySize);
        var performancePerItem = checkDoubleArrayPerformance(ITERATIONS, doubleSorterPerItem, doubleArray);
        var performanceUntilNoChange = checkDoubleArrayPerformance(ITERATIONS, doubleSorterUntilNoChange, doubleArray);
        var performanceWhileNeeded = checkDoubleArrayPerformance(ITERATIONS, doubleSorterWhileNeeded, doubleArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.DOUBLE);
    }

    public Performance checkDoubleBestCasePerformance(int arraySize) {
        var doubleArray = bestCaseDoubleList(arraySize);
        var performancePerItem = checkDoubleArrayPerformance(ITERATIONS, doubleSorterPerItem, doubleArray);
        var performanceUntilNoChange = checkDoubleArrayPerformance(ITERATIONS, doubleSorterUntilNoChange, doubleArray);
        var performanceWhileNeeded = checkDoubleArrayPerformance(ITERATIONS, doubleSorterWhileNeeded, doubleArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.DOUBLE);
    }

    public Performance checkDoubleRandomCasePerformance(int arraySize) {
        var doubleArray = randomCaseDoubleList(arraySize);
        var performancePerItem = checkDoubleArrayPerformance(ITERATIONS, doubleSorterPerItem, doubleArray);
        var performanceUntilNoChange = checkDoubleArrayPerformance(ITERATIONS, doubleSorterUntilNoChange, doubleArray);
        var performanceWhileNeeded = checkDoubleArrayPerformance(ITERATIONS, doubleSorterWhileNeeded, doubleArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.DOUBLE);
    }

    public Performance checkRandomIntegerSortOnSmallerInputs(int size) {
        var intArray = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            intArray.add(i);
        }
        Collections.shuffle(intArray);
        var s = new Integer[intArray.size()];
        s = intArray.toArray(s);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, s);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, s);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, s);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, size, Type.INTEGER);
    }

    public Performance checkBestIntegerSortOnSmallerInputs(int size) {
        var intArray = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            intArray.add(i);
        }
        var s = new Integer[intArray.size()];
        s = intArray.toArray(s);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, s);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, s);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, s);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, size, Type.INTEGER);
    }

    public Performance checkWorstIntegerSortOnSmallerInputs(int size) {
        var intArray = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            intArray.add(i);
        }
        intArray.sort(Collections.reverseOrder());
        var s = new Integer[intArray.size()];
        s = intArray.toArray(s);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, s);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, s);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, s);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, size, Type.INTEGER);
    }
}
