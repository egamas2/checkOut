package com.shop.model;

import com.shop.model.entity.Item;
import com.shop.model.rule.IPromotionalRule;
import com.shop.model.rule.ITotalPriceRule;
import com.shop.model.rule.IUnitPriceRule;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * The responsibility of this class is to scan item by item and apply the rules in two phases. The first phase will
 * account for the unit price rules (method scan()). The second one will deal with the total price rules (Method total())
 */
@RequiredArgsConstructor
public class Checkout {

    @NonNull
    private List<IPromotionalRule> promotionalRules;

    /**
     * At the first phase only the IUnitPrice rules are applied
     * The sums are stored on the intrinsic state of the instances of the rules
     * @param item
     */
    public void scan(Item item) {
        promotionalRules.stream()
                .filter(IUnitPriceRule.class::isInstance)
                .map(IUnitPriceRule.class::cast)
                .forEach(r -> r.check(item));
    }

    /**
     * On the second phase only the rules for totalizing are applied
     * The IUnitPrice rules are traversed to extract the amounts calculated
     * during the first one. Thw minimum of all the total amounts is selected
     * to guarantee that the best deal is applied.
     * @return Total amount as double
     */
    public Double total() {
        Double totalUnit = promotionalRules.stream()
                .filter(IUnitPriceRule.class::isInstance)
                .map(IPromotionalRule::apply)
                .min(Double::compare).orElse(0D);

        promotionalRules.stream()
                .filter(ITotalPriceRule.class::isInstance)
                .forEach(t -> ((ITotalPriceRule) t).setTotal(totalUnit));

        return promotionalRules.stream()
                .filter(ITotalPriceRule.class::isInstance)
                .filter(IPromotionalRule::isApplicable)
                .map(IPromotionalRule::apply)
                .min(Double::compare).orElse(totalUnit);

    }
}
