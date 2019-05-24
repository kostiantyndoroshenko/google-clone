package com.kostiantynd.google.indexing.service;

import com.kostiantynd.google.indexing.util.UrlUtil;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JsoupWebParser implements Parser {
    private static final String HREF_ATTR = "abs:href";
    private static final String LINK_TAG = "a[href]";

    @Override
    public Set<String> extractUrls(Document document) {
        return document.select(LINK_TAG)
                .stream()
                .map(link -> UrlUtil.ridQueryParams(link.attr(HREF_ATTR)))
                .collect(Collectors.toSet());
    }
}
