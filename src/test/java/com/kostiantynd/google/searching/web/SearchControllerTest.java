package com.kostiantynd.google.searching.web;

import com.kostiantynd.google.entity.WebPage;
import com.kostiantynd.google.indexing.service.IndexScheduler;
import com.kostiantynd.google.indexing.web.IndexController;
import com.kostiantynd.google.searching.service.SearchService;
import com.kostiantynd.google.searching.web.dto.SearchRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    private Page<WebPage> response;

    @Before
    public void setUp() {
        response = new PageImpl<>(new ArrayList<>());
    }

    @Test
    public void shouldReturnHttpStatusOkWhenAllRequestParamsAreValid() throws Exception {
        String request = "{ \"phrase\" : \"search me\", \"pageNumber\" : \"1\", \"pageSize\" : \"10\"}";

        when(searchService.search(any(SearchRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/search")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnHttpStatusOkWhenOptionalParamsNotPassed() throws Exception {
        String request = "{ \"phrase\" : \"search me\"}";

        when(searchService.search(any(SearchRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/search")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void returnStatusOkWhenPresent() throws Exception {
        String request = "{ \"phrase\" : \"search me\", \"sortType\" : \"1\"}";

        when(searchService.search(any(SearchRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/search")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

}