package service;

import model.article.Article;
import model.product.DiscountProduct;
import model.product.FixPriceProduct;
import model.product.SimpleProduct;
import model.product.Product;
import model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private Map<UUID, Product> products;
    private Map<UUID, Article> articles;

    public StorageService() {
        // Вызываем метод для заполнения тестовых данных
        fillTestData();
    }

    // Метод для получения всех Searchable объектов
    public List<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }

    // Метод для получения всех продуктов
    public Collection<Product> getAllProducts() {
        return products.values();
    }

    // Метод для получения всех статей
    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    // Заполнение тестовых данных
    private void fillTestData() {
        products = new HashMap<>();
        articles = new HashMap<>();

        // Тестовые данные для продуктов
        Product product1 = new SimpleProduct("Товар 1", 1000, UUID.randomUUID());
        Product product2 = new FixPriceProduct("Товар 2", UUID.randomUUID());
        Product product3 = new DiscountProduct("Товар 3", 1500, 20, UUID.randomUUID());

        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);

        // Тестовые данные для статей
        Article article1 = new Article("Статья 1", "Содержание статьи 1", UUID.randomUUID());
        Article article2 = new Article("Статья 2", "Содержание статьи 2", UUID.randomUUID());

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
    }
}