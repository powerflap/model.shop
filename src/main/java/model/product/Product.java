package model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.search.Searchable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class Product implements Searchable {
    @NotNull
    private final String title;
    @NotNull
    private final UUID id;

    public Product(@NotNull String title, @NotNull UUID id) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым.");
        }
        this.title = title;
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public UUID getId() {
        return id;
    }

    @NotNull
    public abstract int getPrice();

    @Override
    public String toString() {
        return title;
    }

    @JsonIgnore
    @Override
    public String getSearchableTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getSearchableContentKind() {
        return "PRODUCT";
    }
}