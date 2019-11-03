package com.shop.model.rule;

import com.shop.model.util.Utils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class is the implementation of a total price rule.
 * Its responsibility is to check whether the total price passed is bigger than a threshold which will result in
 * applying a percentage of discount.
 */

@Data
@RequiredArgsConstructor
public class TotalRebate implements IPromotionalRule, ITotalPriceRule {

    @NonNull
    private Double thresholdPrice;
    @NonNull
    private Double discount;

    private Double priceSum = 0D;

    @Override
    public boolean isApplicable() {
        return priceSum > thresholdPrice;
    }

    @Override
    public Double apply() {
        return Utils.percentage(priceSum, discount).doubleValue();
    }

    @Override
    public void setTotal(Double total) {
        priceSum = total;
    }
}
