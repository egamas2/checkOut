package com.shop.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal percentage(Double base, Double pct) {
        final BigDecimal bdBase = BigDecimal.valueOf(base);
        final BigDecimal toSustract = bdBase.multiply(BigDecimal.valueOf(pct))
                .divide(ONE_HUNDRED, RoundingMode.HALF_EVEN);
        return bdBase.subtract(toSustract).setScale(2, RoundingMode.HALF_EVEN);
    }
}
