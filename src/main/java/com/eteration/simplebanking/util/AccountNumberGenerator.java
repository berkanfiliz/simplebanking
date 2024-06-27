package com.eteration.simplebanking.util;

import java.util.Random;

public class AccountNumberGenerator {
    public static String generateAccountNumber() {
        Random random = new Random();
        int prefix = 100 + random.nextInt(900);
        int suffix = 1000 + random.nextInt(9000);
        return prefix + "-" + suffix;
    }
}
