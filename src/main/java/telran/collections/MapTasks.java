package telran.collections;

import java.util.*;

public class MapTasks {
    public static void dysplayOccurrences(String[] strings) {
        HashMap<String, Long> occurrenceMap = getMapOccurrences(strings);
        TreeMap<Long, TreeSet<String>> sortedOccurrencesMap = getSortedMapOccurrences(occurrenceMap);
        dysplayMapOccurrences(sortedOccurrencesMap);
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
}
