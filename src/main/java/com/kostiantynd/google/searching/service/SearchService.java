package com.kostiantynd.google.searching.service;

import com.kostiantynd.google.entity.WebPage;
import com.kostiantynd.google.searching.web.dto.SearchRequest;
import org.springframework.data.domain.Page;

public interface SearchService {

    Page<WebPage> search(SearchRequest request);
}
