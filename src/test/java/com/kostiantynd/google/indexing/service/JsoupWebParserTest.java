package com.kostiantynd.google.indexing.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupWebParserTest {

    private Document document;

    @Autowired
    private JsoupWebParser parser;

    @Before
    public void setUp() throws Exception {
        File page = new File("src/test/resources/page.html");
        document = Jsoup.parse(page, "UTF-8", "http://example.com/");
    }

    @Test
    public void shouldExtractPrettyUrlsWithAbsolutePath() {
        Set<String> expectedUrls = new HashSet<>();
        expectedUrls.add("https://en.wikipedia.org/wiki/Web_crawler");
        expectedUrls.add("http://example.com/about");
        expectedUrls.add("http://example1.com");
        expectedUrls.add("http://example2.com");

        Set<String> actualUrls = parser.extractUrls(document);

        Assert.assertEquals(expectedUrls, actualUrls);
    }
}
