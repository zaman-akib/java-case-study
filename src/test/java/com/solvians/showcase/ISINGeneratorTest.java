package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ISINGeneratorTest {

    private ISINGenerator generator = new ISINGenerator();

    @Test
    void testCalculateCheckDigit() {
        assertEquals(6, generator.calculateCheckDigit("DE123456789"));
    }

    @Test
    void testGenerateValidISIN() {
        String ISIN = generator.generate();
        assertEquals(12, ISIN.length());
        assertTrue(Character.isUpperCase(ISIN.charAt(0)));
        assertTrue(Character.isUpperCase(ISIN.charAt(1)));
    }

    @Test
    void testCheckDigitIsValid() {
        String ISIN = generator.generate();
        String withoutCheck = ISIN.substring(0, 11);
        int expected = generator.calculateCheckDigit(withoutCheck);
        assertEquals(expected, Character.getNumericValue(ISIN.charAt(11)));
    }
}