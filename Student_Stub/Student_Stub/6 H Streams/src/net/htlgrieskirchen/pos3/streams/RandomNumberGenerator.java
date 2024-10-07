package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.Random;

public class RandomNumberGenerator {

    public static int[] generateRandomNumbers(int size, int bound) {
        Random random = new Random();
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(bound);
        }

        return numbers;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateRandomNumbers(5, 100)));
    }
}
