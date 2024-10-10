package com.tia102g4.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class PageResponse<T> {

    private List<T> value;

    private Integer currentPage;

    private Integer pageTotal;

    private Integer size;
}
