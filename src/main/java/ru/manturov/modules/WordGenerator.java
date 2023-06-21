package ru.manturov.modules;

import java.util.Random;

public class WordGenerator {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final int MAX_LENGTH = 100;

    public static String generateRndWord() {
        Random random = new Random();
        int length = random.nextInt(MAX_LENGTH) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(
                    alphabet.charAt(
                            random.nextInt(alphabet.length())
                    )
            );
        }
        return sb.toString();
    }
}