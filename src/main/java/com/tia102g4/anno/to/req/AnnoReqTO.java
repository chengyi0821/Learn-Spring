package com.tia102g4.anno.to.req;


import common.AnnoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AnnoReqTO {

	private Long annoId;

	private String startDate;

	private String endDate;

	private String heading;

	private String content;

	private AnnoType type;

	private String image;

	private Boolean deleted;
}
