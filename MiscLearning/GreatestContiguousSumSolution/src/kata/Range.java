package kata;

public class Range {
    public static Iterable<Integer> range(int a, int b){
        return new IntegerRange(a, b);
    }
    
    public static Iterable<Integer> inclusiveRange(int a, int b){
        return new IntegerRange(a, b+1);
    }

}