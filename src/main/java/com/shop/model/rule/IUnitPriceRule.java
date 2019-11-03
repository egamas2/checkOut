package com.shop.model.rule;

import com.shop.model.entity.Item;

public interface IUnitPriceRule {

    /**
     * This method is responsible for updating the internal state needed to calculate the totals for the cases in which
     * the discount applies or not.
     * @param item
     */
    void check(Item item);

}
