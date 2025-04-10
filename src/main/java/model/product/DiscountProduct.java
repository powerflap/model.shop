package model.product;

import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public class DiscountProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountProduct(@NotNull String title, int basePrice, int discount, @NotNull UUID id) {
        super(title, id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена не может быть отрицательной или быть равной нулю");
        }
        this.basePrice = basePrice;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100%");
        }
        this.discount = discount;
    }

    @Override
    public @NotNull int getPrice() {
        return basePrice - (int) ((double) (basePrice * discount) / 100.0);
    }

    @Override
    public String toString() {
        return getTitle() + ": " + getPrice() + " (скидка " + discount + "%)";
    }

    public boolean isSpecial() {
        return true;
    }
}