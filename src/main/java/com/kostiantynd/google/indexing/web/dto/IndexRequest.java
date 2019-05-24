package com.kostiantynd.google.indexing.web.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class IndexRequest {

    @URL(message = "URL must be a valid.")
    private String url;

    @NotNull(message = "Scan depth is a required.")
    @Min(value = 1, message = "Scan depth can't be less than 1.")
    @Max(value = 5, message = "Scan depth can't be greater than 5.")
    private Integer scanDepth = 3;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getScanDepth() {
        return scanDepth;
    }

    public void setScanDepth(Integer scanDepth) {
        this.scanDepth = scanDepth;
    }
}
