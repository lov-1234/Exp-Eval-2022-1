import java.util.List;

public class Performance {

    private final List<Long> passPerItemPerformance;
    private final List<Long> untilNoChangePerformance;
    private final List<Long> whileNeededPerformance;
    private final int sizeTestedFor;
    private final Type type;


    public Performance(List<Long> passPerItemPerformance, List<Long> untilNoChangePerformance, List<Long> whileNeededPerformance, int sizeTestedFor, Type type) {
        this.passPerItemPerformance = passPerItemPerformance;
        this.untilNoChangePerformance = untilNoChangePerformance;
        this.whileNeededPerformance = whileNeededPerformance;
        this.sizeTestedFor = sizeTestedFor;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public int getSizeTestedFor() {
        return sizeTestedFor;
    }

    public List<Long> getWhileNeededPerformance() {
        return whileNeededPerformance;
    }

    public List<Long> getUntilNoChangePerformance() {
        return untilNoChangePerformance;
    }

    public List<Long> getPassPerItemPerformance() {
        return passPerItemPerformance;
    }
}
