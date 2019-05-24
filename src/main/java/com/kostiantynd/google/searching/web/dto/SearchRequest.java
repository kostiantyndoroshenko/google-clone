package com.kostiantynd.google.searching.web.dto;

import com.kostiantynd.google.searching.service.SortType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SearchRequest {

    @NotNull(message = "Searched phrase must be not empty.")
    @Length(max = 50, message = "Searched phrase is too long. Max length is 50 symbols")
    @Length(min = 3, message = "Searched phrase is too small. Min length is 3 symbols")
    private String phrase;

    @Min(value = 0, message = "Invalid page number.")
    private Integer pageNumber = 0;

    @Min(value = 10, message = "Page size can't be less than 10.")
    private Integer pageSize = 10;

    @NotNull(message = "Sort type can't be null.")
    private SortType sortType = SortType.RELEVANT;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}
