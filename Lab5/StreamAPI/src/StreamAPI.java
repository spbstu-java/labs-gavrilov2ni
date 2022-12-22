import java.util.*;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args) {
        System.out.println("============================");
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        System.out.println("Average value of integer list: " + getAverageInt(intList));
        System.out.println("============================");

        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        System.out.println("New strings:");
        getNewString(stringList).forEach(System.out::println);
        System.out.println("============================");

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.0);
        doubleList.add(2.0);
        doubleList.add(3.0);
        doubleList.add(2.0);
        doubleList.add(1.0);
        System.out.println("Square of unique elements:");
        uniqueSquare(doubleList).forEach(System.out::println);
        System.out.println("============================");

        char letter = 't';
        System.out.println("Sorted strings:");
        sortStrings(stringList, letter).forEach(System.out::println);
        System.out.println("============================");

        List<String> emptyList = new ArrayList<>();
        try {
            System.out.println("Last element: " + getLastElement(stringList));
            System.out.println("Last element: " + getLastElement(emptyList));
        } catch (NoSuchElementException e) {
            System.out.println("Last element not found!");
        }
        System.out.println("============================");

        int[] intArray = {1, 2, 3, 4};
        int[] oddIntArray = {1, 3, 5};
        int[] emptyIntArray = {};
        System.out.println("Sum of even values: " + getSumOfEven(intArray));
        System.out.println("Sum of even values: " + getSumOfEven(oddIntArray));
        System.out.println("Sum of even values: " + getSumOfEven(emptyIntArray));
        System.out.println("============================");

        System.out.println("List converted to Map:");
        convertListToMap(stringList).forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("============================");
    }

    public static double getAverageInt(List<Integer> intList){
        return intList.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
    }

    public static List<String> getNewString(List<String> stringList){
        return stringList.stream()
                .map(String::toUpperCase)
                .map(s -> "_new_" + s)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Double> uniqueSquare(List<Double> doubleList){
        return doubleList.stream()
                .distinct()
                .map(n -> n * n)
                .toList();
    }

    public static List<String> sortStrings(List<String> stringList, char letter){
        return stringList.stream()
                .sorted()
                .filter(n -> n.substring(0, 1).equals(String.valueOf(letter)))
                .toList();
    }

    public static <T> T getLastElement(List<T> someList){
        return someList.stream()
                .reduce((a, b) -> b)
                .orElseThrow();
    }

    public static int getSumOfEven(int[] intArray){
        int sum = Arrays.stream(intArray)
                .filter(n -> n % 2 == 0)
                .sum();
        Optional<Integer> intSum = Optional.of(sum);
        return intSum.orElse(0);
    }

    public static Map<String, String> convertListToMap(List<String> stringList){
        return stringList.stream()
                .collect(Collectors.toMap(n -> n.substring(0,1), n -> n.substring(1),
                        (a, b) -> a + ", " + b));
    }
}
