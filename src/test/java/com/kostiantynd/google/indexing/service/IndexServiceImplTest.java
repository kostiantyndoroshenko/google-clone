package com.kostiantynd.google.indexing.service;

import com.kostiantynd.google.indexing.exception.ParserException;
import com.kostiantynd.google.repository.WebPageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexServiceImplTest {

    private Document document;
    private Set<String> urlSet;

    @MockBean
    private Crawler crawler;

    @MockBean
    private Parser parser;

    @MockBean
    private IndexScheduler scheduler;

    @MockBean
    private WebPageRepository repository;

    @Autowired
    private IndexService service;

    @Before
    public void setUp() throws Exception {
        File page = new File("src/test/resources/page.html");
        document = Jsoup.parse(page, "UTF-8", "http://example.com/");

        urlSet = new HashSet<>();
        urlSet.add("http://example1.com");
        urlSet.add("http://example2.com");

        when(crawler.getDocument(anyString())).thenReturn(document);
        when(parser.extractUrls(document)).thenReturn(urlSet);
        when(repository.save(any())).thenReturn(null);
        doNothing().when(scheduler).schedule(anyString(), anyInt());
    }

    @Test
    public void shouldDoNothingWhenCrawlerThrowException() throws Exception {
        when(crawler.getDocument(anyString())).thenThrow(ParserException.class);

        service.indexPage(any(), any());

        verify(parser, never()).extractUrls(any());
        verify(scheduler, never()).schedule(any());
        verify(repository, never()).save(any());
        verify(scheduler, never()).schedule(any());
    }

    @Test
    public void shouldScheduleIndexingWhenScanDepthGreaterThanZeroAnd() throws Exception {
        service.indexPage(anyString(), 1);

        verify(parser, times(1)).extractUrls(document);
        verify(scheduler, times(1)).schedule(urlSet, 0);
    }

    @Test
    public void shouldNotScheduleIndexingWhenScanDepthIsZero() throws Exception {
        service.indexPage(anyString(), 0);

        verify(parser, never()).extractUrls(any());
        verify(scheduler, never()).schedule(anySet(), anyInt());
    }

    @Test
    public void shouldNotScheduleIndexingWhenScanDepthGreaterThanZeroButUrlSetIsEmpty() throws Exception {
        when(parser.extractUrls(document)).thenReturn(new HashSet<>());

        service.indexPage(anyString(), 1);

        verify(parser, times(1)).extractUrls(document);
        verify(scheduler, never()).schedule(anySet(), anyInt());
    }
}