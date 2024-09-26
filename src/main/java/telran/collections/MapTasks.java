package telran.collections;

import java.util.*;
import java.util.stream.*;
import java.util.Map.Entry;

public class MapTasks {
    private static final long COUNT_OF_NUMBERS = 1_000_000;

    public static void dysplayOccurrences(String[] strings) {
        HashMap<String, Long> occurrenceMap = getMapOccurrences(strings);
        TreeMap<Long, TreeSet<String>> sortedOccurrencesMap = getSortedMapOccurrences(occurrenceMap);
        dysplayMapOccurrences(sortedOccurrencesMap);
    }

    public static void dysplayOccurrencesStream(String[] strings) {
        Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream().sorted((e1, e2) -> {
                    int res = Long.compare(e2.getValue(), e1.getValue());
                    return res == 0 ? e1.getKey().compareTo(e2.getKey()) : res;
                }).forEach(e -> System.out.printf("%s -> %d\n", e.getKey(), e.getValue()));
    }

    private static void dysplayMapOccurrences(TreeMap<Long, TreeSet<String>> sortedOccurrencesMap) {
        sortedOccurrencesMap.forEach((occurrence, treeSetOfStrings) -> treeSetOfStrings.stream()
                .forEach(s -> System.out.printf("%s -> %d\n", s, occurrence)));
    }

    private static TreeMap<Long, TreeSet<String>> getSortedMapOccurrences(HashMap<String, Long> occurrenceMap) {
        TreeMap<Long, TreeSet<String>> res = new TreeMap<>(Comparator.reverseOrder());
        occurrenceMap.entrySet().forEach(e -> res.computeIfAbsent(e.getValue(), k -> new TreeSet<>()).add(e.getKey()));
        return res;
    }

    private static HashMap<String, Long> getMapOccurrences(String[] strings) {
        HashMap<String, Long> res = new HashMap<>();
        Arrays.stream(strings).forEach(s -> res.merge(s, 1l, Long::sum));
        return res;
    }

    private static Stream<Integer> steramOfNumbers(int[][] array) {
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed();
    }

    public static Map<Integer, Integer[]> getGroupingByNumberOfDigits(int[][] array) {
        Map<Integer, List<Integer>> map = steramOfNumbers(array)
                .collect(Collectors.groupingBy(n -> Integer.toString(n).length()));
        return map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().toArray(Integer[]::new)));
    }

    public static Map<Integer, Long> getDistributionByNumberOfDigits(int[][] array) {
        return steramOfNumbers(array)
                .collect(Collectors.groupingBy(n -> Integer.toString(n).length(), Collectors.counting()));
    }

    public static void dysplayDigitsDistribution() {
        new Random().ints(0, Integer.MAX_VALUE).limit(COUNT_OF_NUMBERS)
                .flatMap(n -> Integer.toString(n).chars()).boxed()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream().sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> System.out.printf("%c -> %d\n", e.getKey(), e.getValue()));

    }

    public static ParanthesesMaps getParanthesesMaps(Character[][] openCloseParantheses) {
        return new ParanthesesMaps(
                getMap(openCloseParantheses, 0, 1),
                getMap(openCloseParantheses, 1, 0));
    }

    private static Map<Character, Character> getMap(Character[][] openCloseParantheses, int indexOfKey,
            int indexOfValue) {
        return Arrays.stream(openCloseParantheses).collect(Collectors.toMap(c -> c[indexOfKey], c -> c[indexOfValue]));
    }
}
