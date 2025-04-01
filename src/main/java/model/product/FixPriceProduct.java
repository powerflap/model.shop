package model.product;

import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public class FixPriceProduct extends Product {
    private final static int FIX_PRICE = 99;

    public FixPriceProduct(@NotNull String title, @NotNull UUID id) {
        super(title, id);
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getTitle() + ": Фиксированная цена " + FIX_PRICE;
    }


    public boolean isSpecial() {
        return true;
    }
}