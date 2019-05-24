package com.kostiantynd.google.indexing.service;

import com.kostiantynd.google.entity.WebPage;
import com.kostiantynd.google.indexing.exception.ParserException;
import com.kostiantynd.google.repository.WebPageRepository;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class IndexServiceImpl implements IndexService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Parser parser;

    private WebPageRepository repository;

    private IndexScheduler scheduler;

    private Crawler crawler;

    public IndexServiceImpl(Parser parser, WebPageRepository repository, IndexScheduler scheduler, Crawler crawler) {
        this.parser = parser;
        this.repository = repository;
        this.scheduler = scheduler;
        this.crawler = crawler;
    }

    @Override
    public void indexPage(String url, Integer scanDepth) {
        Set<String> urls = Collections.emptySet();

        try {
            Document document = crawler.getDocument(url);

            repository.save(createWebPage(document, url));

            if (scanDepth > 0) {
                urls = parser.extractUrls(document);
            }

            if (!urls.isEmpty()) {
                scheduler.schedule(urls, scanDepth - 1);
            }
        } catch (ParserException e) {
            logger.error("PARSING ERROR: URL - " + url + ", REASON: " + e.getErrorMessage());
        } catch (Exception e) {
            logger.error("INDEX ERROR: " + url + ", REASON: " + e.getMessage());
        }
    }

    private WebPage createWebPage(Document document, String url) {
        WebPage page = new WebPage();
        page.setUrl(url);
        page.setTitle(document.title());
        page.setText(parser.parseHtml(document));

        return page;
    }
}
