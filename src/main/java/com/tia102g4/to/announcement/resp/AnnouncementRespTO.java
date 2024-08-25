package com.tia102g4.to.announcement.resp;

import common.AnnoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementRespTO {

    private Long id;

    private String startDate;

    private String endDate;

    private String heading;

    private String content;

    private AnnoType type;

    private String image;

    private Boolean deleted;
}
