package com.solvians.showcase;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdate implements Callable<String> {

    private final ISINGenerator isinGenerator = new ISINGenerator();

    @Override
    public String call() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        long timestamp = System.currentTimeMillis();
        String isin = isinGenerator.generate();
        double bidPrice = 100.0 + random.nextDouble() * 100.0;
        int bidSize = random.nextInt(1000, 5001);
        double askPrice = 100.0 + random.nextDouble() * 100.0;
        int askSize = random.nextInt(1000, 10001);

        return String.format("%d,%s,%.2f,%d,%.2f,%d", timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }
}