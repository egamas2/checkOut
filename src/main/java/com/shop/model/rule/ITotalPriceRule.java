package com.shop.model.rule;

public interface ITotalPriceRule {

    /**
     * Adds behaviour needed to implement a rule affecting the total
     * The total will be injected in the class, which will be responsible for deciding if the rule must be applied over
     * the this total or not
     * @param totalUnit
     */
    void setTotal(Double totalUnit);
}
