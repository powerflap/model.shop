package model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.search.Searchable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class Article implements Searchable {
    @NotNull
    private final String title;
    @NotNull
    private final String content;
    @NotNull
    private final UUID id;

    public Article(@NotNull String title, @NotNull String content, @NotNull UUID id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    @NotNull
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return title + "\n" + content;
    }

    @JsonIgnore
    @Override
    public String getSearchableTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getSearchableContentKind() {
        return "ARTICLE";
    }
}

