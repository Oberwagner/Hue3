package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;

public class Statistics {
    public static double average(int[] numbers) {
        return Arrays.stream(numbers)
                .average()
                .orElse(0.0);  // 0.0 falls leer
    }

}
