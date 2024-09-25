package telran.collections;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static telran.collections.MapTasks.*;

public class MapTasksTest {
    int[][] array = { { 100, 1, 50 }, { 20, 30 }, { 1 } };

    @Test
    void dysplayOccurrencesTest() {
        String[] strings = { "lpm", "ab", "a", "c", "cb", "cb", "c", "lpm", "lpm" };
        dysplayOccurrences(strings);
    }

    @Test
    void dysplayOccurrencesStreamTest() {
        String[] strings = { "lpm", "ab", "a", "c", "cb", "cb", "c", "lpm", "lpm" };
        dysplayOccurrencesStream(strings);
    }

    @Test
    void getGroupingByNumberOfDigitsTest() {
        Map<Integer, Integer[]> map = getGroupingByNumberOfDigits(array);
        Integer[] oneDigit = { 1, 1 };
        Integer[] twoDigits = { 50, 20, 30 };
        Integer[] threeDigits = { 100 };
        assertArrayEquals(map.get(1), oneDigit);
        assertArrayEquals(map.get(2), twoDigits);
        assertArrayEquals(map.get(3), threeDigits);
    }

    @Test
    void getDistributionByNumberOfDigitsTest() {
        Map<Integer, Long> map = getDistributionByNumberOfDigits(array);
        assertEquals(2, map.get(1));
        assertEquals(3, map.get(2));
        assertEquals(1, map.get(3));
    }

    @Test
    void dysplayDigitsDistributionTest() {
        dysplayDigitsDistribution();
    }

    @Test
    void getParanthesesMapsTest() {
        Character[][] openCloseParantheses = {
                { '[', ']' },
                { '(', ')' },
                { '{', '}' }
        };
        ParanthesesMaps maps = getParanthesesMaps(openCloseParantheses);
        Map<Character, Character> openCloseMap = maps.openCloseMap();
        Map<Character, Character> closeOpenMap = maps.closeOpenMap();
        assertEquals(']', openCloseMap.get('['));
        assertEquals('[', closeOpenMap.get(']'));
    }
}
