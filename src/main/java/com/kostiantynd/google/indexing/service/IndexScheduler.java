package com.kostiantynd.google.indexing.service;

import com.kostiantynd.google.indexing.web.dto.IndexRequest;

import java.util.Set;

public interface IndexScheduler {

    void schedule(IndexRequest request);

    void schedule(String url, Integer depth);

    void schedule(Set<String> urls, Integer depth);
}
