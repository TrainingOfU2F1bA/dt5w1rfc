package practice2;

import org.apache.commons.math3.analysis.function.Add;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal decimal = new BigDecimal(1);
        return products.stream().map(product ->
                getTotle(items,  product)
        ).reduce((a,b)->a.add(b)).get().multiply(decimal.add(tax)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private BigDecimal getTotle(List<OrderItem> items,  Product product) {
        return product.getPrice() .multiply(beDiscounted(product)) .multiply(getCountofProduct(items, product));
    }

    private BigDecimal getCountofProduct(List<OrderItem> items, Product product) {
        return new BigDecimal(findOrderItemByProduct(items,product).getCount());
    }

    private BigDecimal beDiscounted(Product product) {
        BigDecimal decimal = new BigDecimal(1);
        return decimal.subtract(product.getDiscountRate());
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        for (OrderItem item : items)
            if (item.getCode() == product.getCode())  return item;
        return null;
    }

}
