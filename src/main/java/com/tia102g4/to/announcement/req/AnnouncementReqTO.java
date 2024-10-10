package com.tia102g4.to.announcement.req;

import common.AnnoType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Data
@Value
@NoArgsConstructor(force = true)
public class AnnouncementReqTO {

    @NotNull
    String startDate;

    @NotNull
    String endDate;

    @NotNull
    String heading;

    @NotNull
    String content;

    @NotNull
    AnnoType type;

    @NotNull
    String image;

    @NotNull
    Boolean deleted;
}
