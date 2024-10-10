package com.tia102g4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tia102g4.model.Announcement;

import java.util.Date;
import java.util.List;

@Repository
public interface AnnouncementDao extends JpaRepository<Announcement, Long> {

    List<Announcement> findAnnouncementByStartDateAfterAndEndDateBeforeAndHeadingLike(Date startDate,
                                                                                      Date endDate,
                                                                                      String heading);
}
