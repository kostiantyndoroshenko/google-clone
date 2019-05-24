package com.kostiantynd.google.indexing.service;

import com.kostiantynd.google.indexing.exception.ParserException;
import org.jsoup.nodes.Document;

public interface Crawler {

    Document getDocument(String url) throws ParserException;
}
