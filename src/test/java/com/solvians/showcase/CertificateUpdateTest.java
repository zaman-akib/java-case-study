package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateUpdateTest {

    private CertificateUpdate certificateUpdate = new CertificateUpdate();

    @Test
    void testCallReturnsSixFields() {
        String line = certificateUpdate.call();
        String[] parts = line.split(",");
        assertEquals(6, parts.length);
    }

    @Test
    void testTimestampIsValid() {
        String line = certificateUpdate.call();
        long timestamp = Long.parseLong(line.split(",")[0]);
        assertTrue(timestamp > 0);
    }

    @Test
    void testISINHasCorrectLength() {
        String line = certificateUpdate.call();
        String isin = line.split(",")[1];
        assertEquals(12, isin.length());
    }

    @Test
    void testPricesAreInRange() {
        String line = certificateUpdate.call();
        String[] parts = line.split(",");
        double bidPrice = Double.parseDouble(parts[2]);
        double askPrice = Double.parseDouble(parts[4]);
        assertTrue(bidPrice >= 100.0 && bidPrice <= 200.0);
        assertTrue(askPrice >= 100.0 && askPrice <= 200.0);
    }

    @Test
    void testSizesAreInRange() {
        String line = certificateUpdate.call();
        String[] parts = line.split(",");
        int bidSize = Integer.parseInt(parts[3]);
        int askSize = Integer.parseInt(parts[5]);
        assertTrue(bidSize >= 1000 && bidSize <= 5000);
        assertTrue(askSize >= 1000 && askSize <= 10000);
    }
}