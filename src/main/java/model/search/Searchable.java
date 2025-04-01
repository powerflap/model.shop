package model.search;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public interface Searchable {
    @SuppressWarnings("unused")
    @NotNull
    default String getSearchableName() {
        return this.getClass().getSimpleName() + "-" + this.hashCode();
    }

    @NotNull
    UUID getId();

    @NotNull
    String getSearchableTerm();

    @SuppressWarnings("unused")
    @NotNull
    String getSearchableContentKind();
}
