package test;

import exception.NoSuchProductException;
import model.basket.ProductBasket;
import model.basket.UserBasket;
import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.BasketService;
import service.StorageService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BasketServiceTests {

    @InjectMocks
    private BasketService basketService;

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    private UUID validProductId;
    private UUID invalidProductId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validProductId = UUID.randomUUID();
        invalidProductId = UUID.randomUUID();

        // Имитация валидного продукта
        Product validProduct = new Product(validProductId, "Valid Product", 100);
        when(storageService.getProductById(validProductId)).thenReturn(Optional.of(validProduct));

        // Имитация отсутствующего продукта
        when(storageService.getProductById(invalidProductId)).thenReturn(Optional.empty());
    }

    @Test
    void addingNonExistentProductShouldThrowException() {
        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(invalidProductId));
    }

    @Test
    void addingExistingProductShouldCallAddMethodOnProductBasket() {
        basketService.addProductToBasket(validProductId);
        verify(productBasket).addProduct(validProductId);
    }

    @Test
    void emptyBasketReturnsEmptyUserBasket() {
        when(productBasket.getItems()).thenReturn(new HashMap<>());
        UserBasket userBasket = basketService.getUserBasket();
        assertEquals(0, userBasket.getBasketItems().size());
    }

    @Test
    void filledBasketReturnsCorrectUserBasket() {
        Map<UUID, Integer> basketItems = new HashMap<>();
        basketItems.put(validProductId, 2);
        when(productBasket.getItems()).thenReturn(basketItems);

        UserBasket userBasket = basketService.getUserBasket();
        assertEquals(1, userBasket.getBasketItems().size());
        assertEquals(200, userBasket.getTotal().intValue());
    }
}