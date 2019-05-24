package com.kostiantynd.google.indexing.service;

import com.kostiantynd.google.indexing.exception.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class JsoupWebCrawler implements Crawler {
    private static final int WAIT_TIME = 5000;

    @Override
    public Document getDocument(String url) throws ParserException {
        try {
            return Jsoup.connect(url).timeout(WAIT_TIME).get();
        } catch (Exception e) {
            throw new ParserException("Could not be created JSoup document.");
        }
    }
}
