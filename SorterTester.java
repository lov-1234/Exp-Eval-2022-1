import java.io.FileNotFoundException;
import java.io.PrintStream;


public class SorterTester {


    private static final int HUNDRED = 100;
    private static final int SIZE_ONE = 10;
    private static final int SIZE_TWO = 100;
    private static final int SIZE_THREE = 1000;

    /**
     * Method used to test the first hypothesis
     * @param size size of the array
     * @param sorterHelper the sorterHelper instance
     */
    public static void testFirstHypothesis(int size, SorterHelper sorterHelper) {
        Performance performance;
        System.out.println("Checking for Integers");
        System.out.println("Size:" + size);
        performance = sorterHelper.checkWorstIntegerSortOnSmallerInputs(size);

        System.out.println("Performance With PassPerItem:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performance.getPassPerItemPerformance().stream().reduce((long)0, Long::sum)/performance.getPassPerItemPerformance().size());
        performance.getPassPerItemPerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With UntilNoChange:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performance.getUntilNoChangePerformance().stream().reduce((long)0, Long::sum)/performance.getPassPerItemPerformance().size());
        performance.getUntilNoChangePerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With WhileNeeded:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performance.getWhileNeededPerformance().stream().reduce((long)0, Long::sum)/performance.getPassPerItemPerformance().size());
        performance.getWhileNeededPerformance().forEach(i -> System.out.println(i));
        System.out.println();
    }

    /**
     * Method used to test the second hypothesis
     * @param size size of the array
     * @param sorterHelper the sorterHelper instance
     */
    public static void testSecondHypothesis(int size, SorterHelper sorterHelper) {
        Performance performanceOnInts, performanceOnDoubles, performanceOnStrings;
        System.out.println("Checking for Integers");
        System.out.println("Size:" + size);
        performanceOnInts = sorterHelper.checkWorstIntegerSortOnSmallerInputs(size);

        System.out.println("Performance With PassPerItem:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnInts.getPassPerItemPerformance().stream().reduce((long)0, Long::sum)/performanceOnInts.getPassPerItemPerformance().size());
        performanceOnInts.getPassPerItemPerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With UntilNoChange:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnInts.getUntilNoChangePerformance().stream().reduce((long)0, Long::sum)/performanceOnInts.getUntilNoChangePerformance().size());
        performanceOnInts.getUntilNoChangePerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With WhileNeeded:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnInts.getWhileNeededPerformance().stream().reduce((long)0, Long::sum)/performanceOnInts.getWhileNeededPerformance().size());
        performanceOnInts.getWhileNeededPerformance().forEach(i -> System.out.println(i));
        System.out.println();



        System.out.println("Checking for Doubles:");
        System.out.println("Size:" + size);
        performanceOnDoubles = sorterHelper.checkDoubleWorstCasePerformance(size);

        System.out.println("Performance With PassPerItem:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnDoubles.getPassPerItemPerformance().stream().reduce((long)0, Long::sum)/performanceOnDoubles.getPassPerItemPerformance().size());
        performanceOnDoubles.getPassPerItemPerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With UntilNoChange:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnDoubles.getUntilNoChangePerformance().stream().reduce((long)0, Long::sum)/performanceOnDoubles.getUntilNoChangePerformance().size());
        performanceOnDoubles.getUntilNoChangePerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With WhileNeeded:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnDoubles.getWhileNeededPerformance().stream().reduce((long)0, Long::sum)/performanceOnDoubles.getWhileNeededPerformance().size());
        performanceOnDoubles.getWhileNeededPerformance().forEach(i -> System.out.println(i));
        System.out.println();




        System.out.println("Checking for Strings:");
        System.out.println("Size:" + size);
        performanceOnStrings = sorterHelper.checkStringWorstCasePerformanceWithAllSorters(size, HUNDRED);

        System.out.println("Performance With PassPerItem:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnStrings.getPassPerItemPerformance().stream().reduce((long)0, (i, j) -> (i + j))/performanceOnStrings.getPassPerItemPerformance().size());
        performanceOnStrings.getPassPerItemPerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With UntilNoChange:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnStrings.getUntilNoChangePerformance().stream().reduce((long)0, (i, j) -> (i + j))/performanceOnStrings.getUntilNoChangePerformance().size());
        performanceOnStrings.getUntilNoChangePerformance().forEach(i -> System.out.println(i));
        System.out.println();

        System.out.println("Performance With WhileNeeded:");
        System.out.println("Worst Case");
        System.out.println("Average = " + performanceOnStrings.getWhileNeededPerformance().stream().reduce((long)0, (i, j) -> (i + j))/performanceOnStrings.getWhileNeededPerformance().size());
        performanceOnStrings.getWhileNeededPerformance().forEach(i -> System.out.println(i));
        System.out.println();
    }


    public static void main(String[] args) throws FileNotFoundException {
        var sorterHelper = new SorterHelper();
        PrintStream ps = new PrintStream("results.txt");
        System.setOut(ps);

        // Checking for the first Hypothesis:
        System.out.println("HYPOTHESIS ZERO");
        testFirstHypothesis(SIZE_THREE, sorterHelper);
        System.out.println();
        System.out.println();

        // Checking for the second Hypothesis:
        System.out.println("HYPOTHESIS ONE");
        testSecondHypothesis(SIZE_THREE, sorterHelper);
        System.out.println();
        System.out.println();

        // Checking for the Third Hypothesis:
        System.out.println("HYPOTHESIS TWO");
        testFirstHypothesis(SIZE_ONE, sorterHelper);
        testFirstHypothesis(SIZE_TWO, sorterHelper);
        testFirstHypothesis(SIZE_THREE, sorterHelper);
        System.out.println();
        System.out.println();

    }
}
