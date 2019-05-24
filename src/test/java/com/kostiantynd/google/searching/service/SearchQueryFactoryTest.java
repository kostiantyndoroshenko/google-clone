package com.kostiantynd.google.searching.service;

import com.kostiantynd.google.searching.web.dto.SearchRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class SearchQueryFactoryTest {

    private SearchRequest request;
    private SearchQuery query;

    @Before
    public void setUp() throws Exception {
        request = new SearchRequest();
        request.setPhrase("test phrase");
    }

    @Test
    public void shouldReturnQueryWithPageableParams() {
        request.setSortType(SortType.ALPHABET);

        query = SearchQueryFactory.getSearchQuery(request);

        assertEquals(query.getPageable().getPageNumber(), 0);
        assertEquals(query.getPageable().getPageSize(), 10);
    }

    @Test
    public void testReturnedQueryForAlphabetSortType() {
        request.setSortType(SortType.ALPHABET);

        query = SearchQueryFactory.getSearchQuery(request);

        assertFalse(query.getElasticsearchSorts().isEmpty());
    }

    @Test
    public void testReturnedQueryForRelevantSortType() {
        request.setSortType(SortType.RELEVANT);

        query = SearchQueryFactory.getSearchQuery(request);

        assertTrue(query.getElasticsearchSorts().isEmpty());
    }
}