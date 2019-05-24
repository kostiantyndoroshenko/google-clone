package com.kostiantynd.google.searching.service;

import com.kostiantynd.google.entity.WebPage;
import com.kostiantynd.google.repository.WebPageRepository;
import com.kostiantynd.google.searching.web.dto.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private WebPageRepository webPageRepository;

    public SearchServiceImpl(WebPageRepository repository) {
        this.webPageRepository = repository;
    }

    @Override
    public Page<WebPage> search(SearchRequest request) {
        return search(SearchQueryFactory.getSearchQuery(request));
    }

    private Page<WebPage> search(SearchQuery query) {
        return webPageRepository.search(query);
    }
}
