package telran.collections;

import org.junit.jupiter.api.Test;

import static telran.collections.MapTasks.*;

public class MapTasksTest {
    @Test
    void dysplayOccurrencesTest() {
        String[] strings = { "lpm", "ab", "a", "c", "cb", "cb", "c", "lpm", "lpm" };
        dysplayOccurrences(strings);
    }
}
