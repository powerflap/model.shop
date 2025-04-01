package service;

import model.search.SearchResult;
import model.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String pattern) {

        List<Searchable> allItems = storageService.getAllSearchables();


        return allItems.stream()
                .filter(item -> item.getSearchableTerm().contains(pattern))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}