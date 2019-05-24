package com.kostiantynd.google.repository;

import com.kostiantynd.google.entity.WebPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WebPageRepository extends ElasticsearchRepository<WebPage, String> {

}
