package controller.advice;

import exception.NoSuchProductException;
import model.error.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        // Выбираем код ошибки и сообщение
        String errorCode = "PRODUCT_NOT_FOUND";
        String errorMessage = "Товар с идентификатором " + ex.getMessage() + " не найден.";

        ShopError shopError = new ShopError(errorCode, errorMessage);
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}