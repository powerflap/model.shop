package service;

import model.article.Article;
import model.product.DiscountProduct;
import model.product.FixPriceProduct;
import model.product.SimpleProduct;
import model.product.Product;
import model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> availableProducts = new HashMap<>();
    private Map<UUID, Article> articles;

    public StorageService() {

        fillTestData();
    }


    public List<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(articles.values());
        return result;
    }


    public Collection<Article> getAllArticles() {
        return articles.values();
    }


    private void fillTestData() {

        articles = new HashMap<>();


        Product product1 = new SimpleProduct("Товар 1", 1000, UUID.randomUUID());
        Product product2 = new FixPriceProduct("Товар 2", UUID.randomUUID());
        Product product3 = new DiscountProduct("Товар 3", 1500, 20, UUID.randomUUID());

        availableProducts.put(product1.getId(), product1);
        availableProducts.put(product2.getId(), product2);
        availableProducts.put(product3.getId(), product3);


        Article article1 = new Article("Статья 1", "Содержание статьи 1", UUID.randomUUID());
        Article article2 = new Article("Статья 2", "Содержание статьи 2", UUID.randomUUID());

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
    }

    public Optional<Product> getProductById(UUID id) {

        return Optional.empty();
    }}