package com.tia102g4.model;

import java.sql.Date;

public final class AnnouncementBuilder {
    private Long annoId;
    private Date startDate;
    private Date endDate;
    private String heading;
    private String content;
    private Integer type;
    private Byte[] image;
    private Boolean deleted;

    private AnnouncementBuilder() {
    }

    public static AnnouncementBuilder anAnnouncement() {
        return new AnnouncementBuilder();
    }

    public AnnouncementBuilder withAnnoId(Long annoId) {
        this.annoId = annoId;
        return this;
    }

    public AnnouncementBuilder withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public AnnouncementBuilder withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public AnnouncementBuilder withHeading(String heading) {
        this.heading = heading;
        return this;
    }

    public AnnouncementBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public AnnouncementBuilder withType(Integer type) {
        this.type = type;
        return this;
    }

    public AnnouncementBuilder withImage(Byte[] image) {
        this.image = image;
        return this;
    }

    public AnnouncementBuilder withDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Announcement build() {
        Announcement announcement = new Announcement();
        announcement.setAnnoId(annoId);
        announcement.setStartDate(startDate);
        announcement.setEndDate(endDate);
        announcement.setHeading(heading);
        announcement.setContent(content);
        announcement.setType(type);
        announcement.setImage(image);
        announcement.setDeleted(deleted);
        return announcement;
    }
}
