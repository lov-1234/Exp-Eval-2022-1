import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class SorterTester {


    private static final int SIZE_OF_ARRAY = 10;
    private static final Random randomGenerator = new Random();
    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static final int THOUSAND = 1000;


    public static void main(String[] args) {
        var sorterHelper = new SorterHelper();
        Sorter<String> stringSorterPerItem = new BubbleSortPassPerItem<>();
        Sorter<String> stringSorterUntilNoChange = new BubbleSortUntilNoChange<>();
        Sorter<String> stringSorterWhileNeeded = new BubbleSortWhileNeeded<>();

        Sorter<Integer> integerSorterPerItem = new BubbleSortPassPerItem<>();
        Sorter<Integer> integerSorterUntilNoChange = new BubbleSortUntilNoChange<>();
        Sorter<Integer> integerSorterWhileNeeded = new BubbleSortWhileNeeded<>();

        Sorter<Double> doubleSorterPerItem = new BubbleSortPassPerItem<>();
        Sorter<Double> doubleSorterUntilNoChange = new BubbleSortUntilNoChange<>();
        Sorter<Double> doubleSorterWhileNeeded = new BubbleSortWhileNeeded<>();

//        ArrayList<Double> doubleArray = randomGenerator.doubles(SIZE_OF_ARRAY).boxed().collect(Collectors.toCollection(ArrayList::new));
//
//        ArrayList<Integer> intArray = randomGenerator.ints(SIZE_OF_ARRAY).boxed().collect(Collectors.toCollection(ArrayList::new));

        var stringArrayOne = sorterHelper.getStringArrayOfDesiredLength(TEN, SIZE_OF_ARRAY);

        var performance = sorterHelper.checkStringArrayPerformance(100, stringSorterPerItem, stringArrayOne);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterUntilNoChange, stringArrayOne);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterWhileNeeded, stringArrayOne);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);
        var stringArrayTwo = sorterHelper.getStringArrayOfDesiredLength(HUNDRED, SIZE_OF_ARRAY);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterPerItem, stringArrayTwo);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterUntilNoChange, stringArrayTwo);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterWhileNeeded, stringArrayTwo);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);



        var stringArrayThree = sorterHelper.getStringArrayOfDesiredLength(THOUSAND, SIZE_OF_ARRAY);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterPerItem, stringArrayTwo);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterUntilNoChange, stringArrayTwo);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);

        performance = sorterHelper.checkStringArrayPerformance(100, stringSorterWhileNeeded, stringArrayTwo);
        System.out.println(performance.stream().reduce((long)0, (i, j)-> i + j)/100);

//
//
//        var stringArrayLargeOne = sorterHelper.getStringArrayOfDesiredLength(TEN, Math.pow(SIZE_OF_ARRAY, SIZE_OF_ARRAY));
//
//
//        var stringArrayLargeTwo = sorterHelper.getStringArrayOfDesiredLength(HUNDRED, Math.pow(SIZE_OF_ARRAY, SIZE_OF_ARRAY));
//
//
//        var stringArrayLargeThree = sorterHelper.getStringArrayOfDesiredLength(THOUSAND, Math.pow(SIZE_OF_ARRAY, SIZE_OF_ARRAY));




    }
}
