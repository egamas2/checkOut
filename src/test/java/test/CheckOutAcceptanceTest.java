package test;

import com.shop.model.Checkout;
import com.shop.model.entity.Item;
import com.shop.model.rule.IPromotionalRule;
import com.shop.model.rule.ReducedItemPrice;
import com.shop.model.rule.TotalRebate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CheckOutAcceptanceTest {

    private final Item travelCardHolder = new Item(1L, "Travel Card Holder", 9.25D);
    private final Item personalisedCufflinks = new Item(2L, "Personalised cufflinks", 45.00D);
    private final Item tShirt = new Item(3L, "Kids T-shirt", 19.95D);

    private List<IPromotionalRule> promotionalRules = Arrays.asList(
            new TotalRebate(60.00D, 10.0D),
            new ReducedItemPrice(travelCardHolder, 2, 8.50D)
    );

    @Before
    public void init() {
    }

    @Test
    public void givenAListOfDifferentItemsWhenCheckoutIsDoneThenReturnExpectedPrice() {
//        Basket: 001,002,003
//        Total price expected: £66.78

        List<Item> basket = Arrays.asList(
                travelCardHolder,
                personalisedCufflinks,
                tShirt
        );

        Checkout co = new Checkout(promotionalRules);

        basket.forEach(co::scan);

        assertEquals("Total price expected: £66.78 ", Double.valueOf(66.78d), co.total());

    }

    @Test
    public void givenAListOfItemsLessThanTotalRebateWhenCheckoutIsDoneThenReturnExpectedPrice() {
//        Basket: 001,003,001
//        Total price expected: £36.95

        List<Item> basket = Arrays.asList(
                travelCardHolder,
                tShirt,
                travelCardHolder
        );

        Checkout co = new Checkout(promotionalRules);

        basket.forEach(co::scan);

        assertEquals("Total price expected: £36.95 ", Double.valueOf(36.95d), co.total());

    }

    @Test
    public void givenAListOfItemsBiggerThanTotalRebateWhenCheckoutIsDoneThenReturnExpectedPrice() {

//        Basket: 001,002,001,003
//        Total price expected: £73.76

        List<Item> basket = Arrays.asList(
                travelCardHolder,
                personalisedCufflinks,
                travelCardHolder,
                tShirt
        );

        Checkout co = new Checkout(promotionalRules);

        basket.forEach(co::scan);

        assertEquals("Total price expected: £73.76 ", Double.valueOf(73.76d), co.total());

    }

}
