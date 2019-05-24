package com.kostiantynd.google.indexing.service;

import org.jsoup.nodes.Document;

import java.util.Set;

interface Parser {

    Set<String> extractUrls(Document document);
}
