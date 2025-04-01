package controller;

import model.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.SearchService;

import java.util.List;

@RestController
public class ShopController {

    private final SearchService searchService;

    @Autowired
    public ShopController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }
}