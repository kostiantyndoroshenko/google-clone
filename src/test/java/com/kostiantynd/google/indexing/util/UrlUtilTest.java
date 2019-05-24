package com.kostiantynd.google.indexing.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UrlUtilTest {

    @Test
    public void shouldRidParamsAfterHashSymbol() {
        String url = "http://example.com/#/any-params";

        String expectedUrl = "http://example.com/";

        String actualUrl = UrlUtil.ridQueryParams(url);

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void shouldRidParamsAfterQuestionMarkSymbol() {
        String url = "http://example.com/?any=params";

        String expectedUrl = "http://example.com/";

        String actualUrl = UrlUtil.ridQueryParams(url);

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void shouldNotRidParamsWhenSuchNotPresent() {
        String url = "http://example.com/";

        String expectedUrl = "http://example.com/";

        String actualUrl = UrlUtil.ridQueryParams(url);

        assertEquals(expectedUrl, actualUrl);
    }
}
