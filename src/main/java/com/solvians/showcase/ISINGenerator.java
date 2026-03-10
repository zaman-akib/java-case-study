package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class ISINGenerator {

    private static final int UPPERCASE_ALPHABET_LENGTH = 2;
    private static final int ALPHANUMERIC_CHAR_LENGTH = 9;
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder ISIN = new StringBuilder();

        for (int i = 0; i < UPPERCASE_ALPHABET_LENGTH; i++) {
            ISIN.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }

        for (int i = 0; i < ALPHANUMERIC_CHAR_LENGTH; i++) {
            ISIN.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

        ISIN.append(calculateCheckDigit(ISIN.toString()));
        return ISIN.toString();
    }

    int calculateCheckDigit(String ISINWithoutCheckDigit) {
        StringBuilder converted = new StringBuilder();
        for (char c : ISINWithoutCheckDigit.toCharArray()) {
            if (Character.isLetter(c)) {
                converted.append(c - 'A' + 10);
            } else {
                converted.append(c);
            }
        }

        String digits = converted.toString();
        int sum = 0;
        for (int i = digits.length() - 1; i >= 0; i--) {
            int d = digits.charAt(i) - '0';
            if ((digits.length() - 1 - i) % 2 == 0) {
                d *= 2;
            }
            sum += d / 10 + d % 10;
        }

        int remainder = sum % 10;
        return remainder == 0 ? 0 : 10 - remainder;
    }
}