package com.kostiantynd.google.searching.web;

import com.kostiantynd.google.entity.WebPage;
import com.kostiantynd.google.searching.service.SearchService;
import com.kostiantynd.google.searching.web.dto.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("api/search")
    public @ResponseBody ResponseEntity<Page<WebPage>> search(@RequestBody @Valid SearchRequest request)  {
        return new ResponseEntity<>(searchService.search(request) , HttpStatus.OK);
    }
}
