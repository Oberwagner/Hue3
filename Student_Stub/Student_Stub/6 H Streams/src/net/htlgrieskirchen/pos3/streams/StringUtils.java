package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    public static List<String> upperCase(String[] strings) {
        return Arrays.stream(strings)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
