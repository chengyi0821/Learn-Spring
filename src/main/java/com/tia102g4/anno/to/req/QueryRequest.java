package com.tia102g4.anno.to.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequest {
	private String searchQuery;
    private String startDate;
    private String endDate;
}
