package service;
import exception.NoSuchProductException;
import model.basket.BasketItem;
import model.basket.ProductBasket;
import model.basket.UserBasket;
import model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> optionalProduct = storageService.getProductById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoSuchProductException(id);
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> items = productBasket.getItems();
        List<BasketItem> basketItems = items.entrySet().stream()
                .map(entry -> {
                    Optional<Product> optionalProduct = storageService.getProductById(entry.getKey());
                    return optionalProduct.map(product -> new BasketItem(product, entry.getValue())).orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new UserBasket(basketItems);
    }
}