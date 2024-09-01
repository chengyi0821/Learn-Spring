package com.tia102g4.common.response;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> value;

    private Long currentPage;

    private Long pageTotal;

    private Long count;
}
