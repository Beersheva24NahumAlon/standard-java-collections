package telran.collections;

import java.util.Map;

public record ParanthesesMaps(Map<Character, Character> openCloseMap,
        Map<Character, Character> closeOpenMap) {

}
