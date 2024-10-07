package net.htlgrieskirchen.pos3.streams;

import java.util.stream.IntStream;

public class OddSquareSum {
    public static void main(String[] args) {
        final int result = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(n -> n % 2 != 0) // ungerade zahlen weg
                .map(n -> n * n)        // Mal rechnen
                .reduce(0, Integer::sum); // summieren

        System.out.println("Summe der quadrierten ungeraden Zahlen von 1 bis 10: " + result);
    }
}
