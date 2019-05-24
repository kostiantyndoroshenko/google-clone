package com.kostiantynd.google.searching.service;

import com.kostiantynd.google.searching.web.dto.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

class SearchQueryFactory {

    private static final String TEXT_COLUMN = "text";
    private static final String TITLE_COLUMN = "title";

    static SearchQuery getSearchQuery(SearchRequest request) {
        if (request.getSortType() == SortType.ALPHABET) {
            return alphabetSearchQuery(request).build();
        }

        return relevantSearchQuery(request).build();
    }

    private static NativeSearchQueryBuilder relevantSearchQuery(SearchRequest request) {
        return setupBuilder(request);
    }

    private static NativeSearchQueryBuilder alphabetSearchQuery(SearchRequest request) {
        return setupBuilder(request)
                .withSort(SortBuilders.fieldSort(TITLE_COLUMN).order(SortOrder.ASC));
    }


    private static NativeSearchQueryBuilder setupBuilder(SearchRequest request) {
        return new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery(TEXT_COLUMN, request.getPhrase()))
                .withPageable(PageRequest.of(request.getPageNumber(), request.getPageSize()));
    }
}
