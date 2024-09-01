package com.tia102g4.service;

import com.tia102g4.common.response.PageResponse;
import com.tia102g4.dao.AnnouncementDao;
import com.tia102g4.model.Announcement;
import com.tia102g4.to.announcement.req.AnnouncementReqTO;
import com.tia102g4.to.announcement.resp.AnnouncementRespTO;
import common.AnnoType;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementDao announcementDao;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public void create(AnnouncementReqTO reqTO) {

    }

    public PageResponse<AnnouncementRespTO> getByPageAndCount(Long page, Long count) {
        List<AnnouncementRespTO> resTOs = announcementDao.findByPageAndCount(page, count)
                                                         .stream()
                                                         .map(this::asAnnouncementRespTO)
                                                         .collect(Collectors.toList());
        Long pageTotal = announcementDao.countTotalPage(count);
        return new PageResponse<>(resTOs, page, pageTotal, count);
    }

    private AnnouncementRespTO asAnnouncementRespTO(Announcement announcement) {
        return AnnouncementRespTO.builder()
                                 .id(announcement.getAnnoId())
                                 .startDate(dateFormat.format(announcement.getStartDate()))
                                 .endDate(dateFormat.format(announcement.getEndDate()))
                                 .heading(announcement.getHeading()).content(announcement.getContent())
                                 .type(null)
                                 .image(null)
                                 .deleted(announcement.getDeleted())
                                 .build();
    }
}
