
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

    private static final int ITERATIONS  = 500;
    private static final int WARMUP = 100;


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

    /**
     * Generates an alphanumeric string randomly
     * @param n the size of the string
     * @return the String that was generated randomly
     */
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

    /**
     * Converts a List of string to the corresponding array with unchanged elements
     * @param stringArray The List of strings
     * @return the list converted to an array
     */
    private String[] listToStringArray(List<String> stringArray) {
        String[] s = new String[stringArray.size()];
        s = stringArray.toArray(s);
        return s;
    }

    /**
     * Gets a string array of the desired length
     * @param size The size of the strings
     * @param desiredArraySize The size of the array
     * @return Array of alphanumeric strings of given size
     */
    public String[] getStringArrayOfDesiredLength(int size, int desiredArraySize) {
        var stringArray = new ArrayList<String>();
        for (var i = 0; i < desiredArraySize; i++) {
            stringArray.add(getAlphaNumericString(size));
        }
        return listToStringArray(stringArray);
    }

    /**
     * Returns the copy of the string array
     * @param stringArray Array for which copy has to be made
     * @return the array copy
     */
    public String[] returnStringArrayCopy(String[] stringArray) {
        var s = new String[stringArray.length];
        System.arraycopy(stringArray, 0, s, 0, stringArray.length);
        return s;
    }

    /**
     * Returns the copy of the double array
     * @param doubleArray Array for which copy has to be made
     * @return the array copy
     */
    public Double[] returnDoubleArrayCopy(Double[] doubleArray) {
        var s = new Double[doubleArray.length];
        System.arraycopy(doubleArray, 0, s, 0, doubleArray.length);
        return s;
    }

    /**
     * Returns the copy of the integer array
     * @param integerArray Array for which copy has to be made
     * @return the array copy
     */
    public Integer[] returnIntegerArrayCopy(Integer [] integerArray) {
        var s = new Integer[integerArray.length];
        System.arraycopy(integerArray, 0, s, 0, integerArray.length);
        return s;
    }

    /**
     * Checks the sorting performance of the given string array with the corresponding sorter for specified iterations
     * @param iterations The number of iterations to check the performance for
     * @param sorter The sorting algorithm
     * @param stringArray the Array needed to be sorted
     * @return The list of Execution time for each performance
     */
    public List<Long> checkStringArrayPerformance(int iterations, Sorter<String> sorter, String[] stringArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnStringArrayCopy(stringArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            if (i > WARMUP) // Warmup done (same for the other methods)
                timeList.add(endTime - startTime);
        }
        return timeList;
    }

    /**
     * Checks the sorting performance of the given double array with the corresponding sorter for specified iterations
     * @param iterations The number of iterations to check the performance for
     * @param sorter The sorting algorithm
     * @param doubleArray the Array needed to be sorted
     * @return The list of Execution time for each performance
     */
    public List<Long> checkDoubleArrayPerformance(int iterations, Sorter<Double> sorter, Double[] doubleArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnDoubleArrayCopy(doubleArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            if (i > WARMUP)
                timeList.add(endTime - startTime);
        }
        return timeList;
    }

    /**
     * Checks the sorting performance of the given integer array with the corresponding sorter for specified iterations
     * @param iterations The number of iterations to check the performance for
     * @param sorter The sorting algorithm
     * @param integerArray the Array needed to be sorted
     * @return The list of Execution time for each performance
     */
    public List<Long> checkIntegerArrayPerformance(int iterations, Sorter<Integer> sorter, Integer[] integerArray) {
        var timeList = new ArrayList<Long>();
        for (int i = 0; i < iterations; i++) {
            var copy = returnIntegerArrayCopy(integerArray);
            var startTime = System.nanoTime();
            sorter.sort(copy);
            var endTime = System.nanoTime();
            if (i > WARMUP)
                timeList.add(endTime - startTime);
        }
        return timeList;
    }

    /**
     * Generates a worst case, that is reverse sorted array of doubles
     * @param size The size of the list to be created
     * @return The Array of doubles reversely sorted
     */
    public Double[] worstCaseDoubleList(int size) {
        ArrayList<Double> doubleArray = randomGenerator.doubles(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Double[doubleArray.size()];
        doubleArray.sort(Collections.reverseOrder());
        s = doubleArray.toArray(s);
        return s;
    }

    /**
     * Generates a best case, that is sorted array of doubles
     * @param size The size of the list to be created
     * @return The Array of doubles sorted
     */
    public Double[] bestCaseDoubleList(int size) {
        ArrayList<Double> doubleArray = randomGenerator.doubles(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Double[doubleArray.size()];
        Collections.sort(doubleArray);
        s = doubleArray.toArray(s);
        return s;
    }

    /**
     * Generates a worst case, that is reverse sorted array of integers
     * @param size The size of the list to be created
     * @return The Array of integers reversely sorted
     */
    public Integer[] worstCaseIntegerList(int size) {
        ArrayList<Integer> integerArray = randomGenerator.ints(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Integer[integerArray.size()];
        integerArray.sort(Collections.reverseOrder());
        s = integerArray.toArray(s);
        return s;
    }

    /**
     * Generates a best case, that is reverse sorted array of integers
     * @param size The size of the list to be created
     * @return The Array of integers reversely sorted
     */
    public Integer[] bestCaseIntegerList(int size) {
        ArrayList<Integer> integerArray = randomGenerator.ints(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Integer[integerArray.size()];
        Collections.sort(integerArray);
        s = integerArray.toArray(s);
        return s;
    }

    /**
     * Generates a random case, that is unsorted array of doubles
     * @param size The size of the list to be created
     * @return The Array of doubles unsorted
     */
    public Double[] randomCaseDoubleList(int size) {
        ArrayList<Double> doubleArray = randomGenerator.doubles(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Double[doubleArray.size()];
        s = doubleArray.toArray(s);
        return s;
    }

    /**
     * Generates a random case, that is unsorted array of doubles
     * @param size The size of the list to be created
     * @return The Array of integers unsorted
     */
    public Integer[] randomCaseIntegerList(int size) {
        ArrayList<Integer> integerArray = randomGenerator.ints(size).boxed().collect(Collectors.toCollection(ArrayList::new));
        var s = new Integer[integerArray.size()];
        s = integerArray.toArray(s);
        return s;
    }

    /**
     * Checks the perfomance of the random case string array of a given size, with the strings of given size as well, with all the sorters
     * @param size The size of the array
     * @param stringSize The size of the strings
     * @return the performance data of the string sorter
     * @see Performance
     */
    public Performance checkStringRandomCasePerformanceWithAllSorters(int size, int stringSize){
        var stringArrayOne = getStringArrayOfDesiredLength(stringSize, size);
        var performancePerItem = checkStringArrayPerformance(ITERATIONS, stringSorterPerItem, stringArrayOne);
        var performanceUntilNoChange = checkStringArrayPerformance(ITERATIONS, stringSorterUntilNoChange, stringArrayOne);
        var performanceWhileNeeded = checkStringArrayPerformance(ITERATIONS, stringSorterWhileNeeded, stringArrayOne);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, size, Type.STRING);
    }

    /**
     * Checks the perfomance of the worst case string array of a given size, with the strings of given size as well, with all the sorters
     * @param size The size of the array
     * @param stringSize The size of the strings
     * @return the performance data of the string sorter
     * @see Performance
     */
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

    /**
     * Checks the perfomance of the best case string array of a given size, with the strings of given size as well, with all the sorters
     * @param size The size of the array
     * @param stringSize The size of the strings
     * @return the performance data of the string sorters
     * @see Performance
     */
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

    /**
     * Checks the perfomance of the worst case integer array of a given size, with all the sorters
     * @param arraySize The size of the array to check the performance for
     * @return the performance data of the integer sorters
     * @see Performance
     */
    public Performance checkIntegerWorstCasePerformance(int arraySize) {
        var integerArray = worstCaseIntegerList(arraySize);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, integerArray);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, integerArray);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, integerArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.INTEGER);
    }

    /**
     * Checks the perfomance of the best case integer array of a given size, with all the sorters
     * @param arraySize The size of the array to check the performance for
     * @return the performance data of the integer sorters
     * @see Performance
     */
    public Performance checkIntegerBestCasePerformance(int arraySize) {
        var integerArray = bestCaseIntegerList(arraySize);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, integerArray);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, integerArray);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, integerArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.INTEGER);
    }

    /**
     * Checks the perfomance of the random case integer array of a given size, with all the sorters
     * @param arraySize The size of the array to check the performance for
     * @return the performance data of the integer sorters
     * @see Performance
     */
    public Performance checkIntegerRandomCasePerformance(int arraySize) {
        var integerArray = randomCaseIntegerList(arraySize);
        var performancePerItem = checkIntegerArrayPerformance(ITERATIONS, integerSorterPerItem, integerArray);
        var performanceUntilNoChange = checkIntegerArrayPerformance(ITERATIONS, integerSorterUntilNoChange, integerArray);
        var performanceWhileNeeded = checkIntegerArrayPerformance(ITERATIONS, integerSorterWhileNeeded, integerArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.INTEGER);
    }

    /**
     * Checks the perfomance of the worst case double array of a given size, with all the sorters
     * @param arraySize The size of the array to check the performance for
     * @return the performance data of the double sorters
     * @see Performance
     */
    public Performance checkDoubleWorstCasePerformance(int arraySize) {
        var doubleArray = worstCaseDoubleList(arraySize);
        var performancePerItem = checkDoubleArrayPerformance(ITERATIONS, doubleSorterPerItem, doubleArray);
        var performanceUntilNoChange = checkDoubleArrayPerformance(ITERATIONS, doubleSorterUntilNoChange, doubleArray);
        var performanceWhileNeeded = checkDoubleArrayPerformance(ITERATIONS, doubleSorterWhileNeeded, doubleArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.DOUBLE);
    }

    /**
     * Checks the perfomance of the best case double array of a given size, with all the sorters
     * @param arraySize The size of the array to check the performance for
     * @return the performance data of the double sorters
     * @see Performance
     */
    public Performance checkDoubleBestCasePerformance(int arraySize) {
        var doubleArray = bestCaseDoubleList(arraySize);
        var performancePerItem = checkDoubleArrayPerformance(ITERATIONS, doubleSorterPerItem, doubleArray);
        var performanceUntilNoChange = checkDoubleArrayPerformance(ITERATIONS, doubleSorterUntilNoChange, doubleArray);
        var performanceWhileNeeded = checkDoubleArrayPerformance(ITERATIONS, doubleSorterWhileNeeded, doubleArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.DOUBLE);
    }

    /**
     * Checks the perfomance of the random case integer array of a given size, with all the sorters
     * @param arraySize The size of the array to check the performance for
     * @return the performance data of the integer sorters
     * @see Performance
     */
    public Performance checkDoubleRandomCasePerformance(int arraySize) {
        var doubleArray = randomCaseDoubleList(arraySize);
        var performancePerItem = checkDoubleArrayPerformance(ITERATIONS, doubleSorterPerItem, doubleArray);
        var performanceUntilNoChange = checkDoubleArrayPerformance(ITERATIONS, doubleSorterUntilNoChange, doubleArray);
        var performanceWhileNeeded = checkDoubleArrayPerformance(ITERATIONS, doubleSorterWhileNeeded, doubleArray);
        return new Performance(performancePerItem, performanceUntilNoChange, performanceWhileNeeded, arraySize, Type.DOUBLE);
    }

    /**
     * Checks the performance of the sorting algorithms on smaller integer values in an array of a given size, in random order
     * @param size The size of the array to check the performance for
     * @return the Performance data of the sorters.
     * @see Performance
     */
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

    /**
     * Checks the performance of the sorting algorithms on smaller integer values in an array of a given size, in best order
     * @param size The size of the array to check the performance for
     * @return the Performance data of the sorters.
     * @see Performance
     */
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

    /**
     * Checks the performance of the sorting algorithms on smaller integer values in an array of a given size, in worst order
     * @param size The size of the array to check the performance for
     * @return the Performance data of the sorters.
     * @see Performance
     */
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
