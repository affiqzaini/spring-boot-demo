package com.group.demo.dto.general;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a predefined format for API with paged data response.
 * The format is meant to be changed according to the practice used by any
 * development team.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedData<T> {

    @JsonIgnore
    private Class<?> dtoClass;

    @JsonIgnore
    private List<T> rawData;

    private int statusCode = 200;
    private boolean status = true;
    private String statusMessage = "Data retrieved successfully.";
    private List<Object> data;
    private CustomPagination pagination;

    @SuppressWarnings("unchecked")
    public PagedData(Page<T> page) {
        if (page.hasContent()) {
            this.data = (List<Object>) page.getContent();
        } else {
            this.data = new ArrayList<>();
        }
        this.pagination = new CustomPagination(page);
    }

    public PagedData(Page<T> page, Class<?> dtoConverter) {
        if (page.hasContent()) {
            this.rawData = page.getContent();
            this.dtoClass = dtoConverter;
            convertDataToModel();
        } else {
            this.data = new ArrayList<>();
        }

        this.pagination = new CustomPagination(page);
    }

    public void convertDataToModel() {
        this.data = new ArrayList<>();

        for (T item : rawData) {
            try {
                @SuppressWarnings("unchecked")
                DtoConverter<T> dto = (DtoConverter<T>) this.dtoClass.getDeclaredConstructor().newInstance();
                dto.convert(item);
                this.data.add(dto);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class CustomPagination {
        int page;
        int totalPages;
        int pageSize;
        int totalItems;
        boolean hasNext;
        boolean hasPrevious;
        boolean isFirst;
        boolean isLast;
        Sort sort;

        public CustomPagination(Page<?> page) {
            this.page = page.getNumber() + 1;
            this.totalPages = page.getTotalPages();
            this.pageSize = page.getSize();
            this.totalItems = page.getNumberOfElements();
            this.hasNext = page.hasNext();
            this.hasPrevious = page.hasPrevious();
            this.isFirst = page.isFirst();
            this.isLast = page.isLast();
            this.sort = page.getSort();
        }
    }

}
