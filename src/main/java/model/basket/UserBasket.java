package model.basket;

import model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItems;
    private final BigDecimal total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.total = calculateTotal(basketItems);
    }

    private BigDecimal calculateTotal(List<BasketItem> basketItems) {
        return basketItems.stream()
                .map(item -> BigDecimal.valueOf(item.getProduct().getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public BigDecimal getTotal() {
        return total;
    }
}