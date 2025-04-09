package controller;

import model.basket.UserBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.BasketService;

import java.util.UUID;

@RestController
public class ShopController {
    private final BasketService basketService;

    @Autowired
    public ShopController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProductToBasket(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}