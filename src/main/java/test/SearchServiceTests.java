package test;

import model.search.SearchResult;
import model.search.Searchable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.SearchService;
import service.StorageService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SearchServiceTests {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private StorageService storageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEmptyWhenNoItemsInStorage() {
        when(storageService.getAllSearchables()).thenReturn(List.of());
        List<SearchResult> results = searchService.search("test");
        assertEquals(0, results.size());
    }

    @Test
    void shouldReturnEmptyWhenNoMatchingItems() {
        Searchable mockSearchable = () -> "other-term";
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(mockSearchable));
        List<SearchResult> results = searchService.search("test");
        assertEquals(0, results.size());
    }

    @Test
    void shouldFindMatchingItem() {
        Searchable matchingSearchable = () -> "test-item";
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(matchingSearchable));
        List<SearchResult> results = searchService.search("test");
        assertEquals(1, results.size());
    }
}