package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.Random;

public class RandomStringGenerator {
    public static String[] generateRandomStrings(int size, int length) {
        Random random = new Random();
        String[] strings = new String[size];
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            strings[i] = sb.toString();
        }

        return strings;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateRandomStrings(5, 5)));
    }
}
