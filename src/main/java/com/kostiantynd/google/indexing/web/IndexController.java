package com.kostiantynd.google.indexing.web;

import com.kostiantynd.google.indexing.service.IndexScheduler;
import com.kostiantynd.google.indexing.web.dto.IndexRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class IndexController {

    private final IndexScheduler indexScheduler;

    public IndexController(IndexScheduler indexScheduler) {
        this.indexScheduler = indexScheduler;
    }

    @PostMapping("api/index")
    @ResponseStatus(HttpStatus.OK)
    public void indexPage(@RequestBody @Valid IndexRequest request) {
        indexScheduler.schedule(request);
    }
}
