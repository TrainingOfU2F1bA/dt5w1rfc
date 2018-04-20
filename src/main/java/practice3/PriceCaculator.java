package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    public BigDecimal calculateOriginTotal(List<OrderLineItem> orderLineItemList) {
        return orderLineItemList.stream().map(OrderLineItem::getPrice).reduce((a,b)->a.add(b)).get();
    }

    public BigDecimal calulateDiscountedTotal(List<BigDecimal> discounts, BigDecimal subTotal) {
        return discounts.stream().reduce(subTotal,(a,b)->a.subtract(b));
    }

    public BigDecimal addTax(BigDecimal subTotal, BigDecimal tax) {
       return subTotal.add(subTotal.multiply(tax));
    }
}
