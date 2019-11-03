package com.shop.model.rule;

import com.shop.model.entity.Item;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class is the implementation of a unit price rule.
 * Its responsibility is to detect if the specified item is present more than x times and, in this case, apply a
 * reduced unit price to the total, for each item scanned.
 */

@Data
@RequiredArgsConstructor
public class ReducedItemPrice implements IPromotionalRule, IUnitPriceRule {

    @NonNull
    private Item item;
    @NonNull
    private Integer itemNumberThreshold;
    @NonNull
    private Double finalUnitPrice;

    private Integer itemCounter = 0;

    private double totalSum = 0L;
    private double totalSumIfDiscountApplied = 0L;

    @Override
    public boolean isApplicable() {
        if (itemCounter >= itemNumberThreshold) {
            return true;
        }
        return false;
    }

    @Override
    public void check(Item item) {
        if (this.item.equals(item)) {
            itemCounter++;
            totalSumIfDiscountApplied += finalUnitPrice;
        } else {
            totalSumIfDiscountApplied += item.getDefaultPrice();
        }
        totalSum += item.getDefaultPrice();
    }

    @Override
    public Double apply() {
        if (isApplicable()) {
            return totalSumIfDiscountApplied;
        }
        return totalSum;
    }
}
