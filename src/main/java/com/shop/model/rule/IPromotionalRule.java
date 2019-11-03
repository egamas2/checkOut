package com.shop.model.rule;

/**
 * This interface is the root of the promotional rules.
 */
public interface IPromotionalRule {

    /**
     * Checks if the rule is going to be applied.
     * It will find out using the internal state which will be updated on the propper phase
     * @return true if the conditions to get the discount have been met.
     */
    boolean isApplicable();

    /**
     * Returns the total price with the deal applied if isApplicable() is true.
     * @return double expressing the total amount with the deal
     */
    Double apply();

}
