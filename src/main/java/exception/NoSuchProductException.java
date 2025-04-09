package exception;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(UUID id) {
        super("Товар с идентификатором " + id + " не найден.");
    }
}