package com.kostiantynd.google.indexing.web;

import com.kostiantynd.google.indexing.service.IndexScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndexScheduler schedulerMock;

    @Test
    public void shouldReturnHttpStatusOkWhenAllRequestParamsAreValid() throws Exception {
        String request = "{ \"url\" : \"http://example.com\", \"scanDepth\" : \"1\"}";

        mockMvc.perform(post("/api/index")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnHttpStatusBadRequestWhenScanDepthGreaterThan5() throws Exception {
        String request = "{ \"url\" : \"http://example.com\", \"scanDepth\" : \"6\"}";

        mockMvc.perform(post("/api/index")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnHttpStatusBadRequestWhenScanDepthLessThan1() throws Exception {
        String request = "{ \"url\" : \"http://example.com\", \"scanDepth\" : \"0\"}";

        mockMvc.perform(post("/api/index")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnHttpStatusBadRequestWhenUrlIsNotValid() throws Exception {
        String request = "{ \"url\" : \"invalid url\", \"scanDepth\" : \"3\"}";

        mockMvc.perform(post("/api/index")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }
}