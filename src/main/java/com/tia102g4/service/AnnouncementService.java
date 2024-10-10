package com.tia102g4.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tia102g4.anno.to.req.AnnoDeleteReqTO;
import com.tia102g4.common.response.PageResponse;
import com.tia102g4.dao.AnnouncementDao;
import com.tia102g4.dao.AnnouncementRepository;
import com.tia102g4.model.Announcement;
import com.tia102g4.to.announcement.resp.AnnouncementRespTO;
import com.tia102g4.util.Base64Util;

import common.AnnoType;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementDao announcementDao;
    private final AnnouncementRepository repository;
    private final Base64Util base64Util;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //新增跟修改並用 使用了SpringDataJPA的save()方法
    public void saveOrUpdate(AnnouncementRespTO reqTO) {
        Announcement announcement = asAnnouncement(reqTO);
        announcementDao.save(announcement);
    }

    public void delete(AnnoDeleteReqTO reqTO) {
        Announcement announcement = Announcement.builder()
                                                .annoId(reqTO.getId())
                                                .deleted(reqTO.getDeleted())
                                                .build();
        announcementDao.delete(announcement);
    }

    public List<AnnouncementRespTO> getAnnosByCompositeQuery(Map<String, String> map) throws ParseException {
        List<Announcement> announcements = repository.findByCompositeQuery(map);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(map.get("startDate"));
        Date endDate = formatter.parse(map.get("endDate"));

        val announcements2 = announcementDao.findAnnouncementByStartDateAfterAndEndDateBeforeAndHeadingLike(startDate, endDate, map.get("heading"));
        return announcements.stream()
                            .map(this::asAnnouncementRespTO)
                            .collect(Collectors.toList());
    }

    public PageResponse<AnnouncementRespTO> getByPageAndCount(Integer page, Integer size) {
        // 使用 PageRequest 來建立 Pageable 物件
        Pageable pageable = PageRequest.of(page - 1, size);
        // 呼叫 DAO 進行分頁查詢
        Page<Announcement> announcementPage = announcementDao.findAll(pageable);

        // 將查詢結果轉換成 AnnouncementRespTO
        List<AnnouncementRespTO> resTOs = announcementPage.getContent()
                                                          .stream()
                                                          .map(this::asAnnouncementRespTO)
                                                          .collect(Collectors.toList());
        // 返回分頁結果
        return new PageResponse<AnnouncementRespTO>(resTOs, page, announcementPage.getTotalPages(), size);
    }

    // 將 Announcement 轉換成 AnnouncementRespTO
    private AnnouncementRespTO asAnnouncementRespTO(Announcement announcement) {
        return AnnouncementRespTO.builder()
                                 .id(announcement.getAnnoId())
                                 .startDate(dateFormat.format(announcement.getStartDate()))
                                 .endDate(dateFormat.format(announcement.getEndDate()))
                                 .heading(announcement.getHeading())
                                 .content(announcement.getContent())
                                 .type(AnnoType.fromTypeId(announcement.getType()))
                                 .image(announcement.getImage() != null ? base64Util.byteArrayTobase64(announcement.getImage()) : null) // 暫時先用三元式運算 因為是以postman測試
                                 .deleted(announcement.getDeleted())
                                 .build();
    }

    // 將 AnnouncementRespTO 轉換成 Announcement
    private Announcement asAnnouncement(AnnouncementRespTO respTO) {
        java.util.Date startDate;
        java.util.Date endDate;
        try {
            startDate = dateFormat.parse(respTO.getStartDate());
            endDate = dateFormat.parse(respTO.getEndDate());
            return Announcement.builder()
                               .annoId(respTO.getId())
                               .startDate(new java.sql.Date(startDate.getTime()))
                               .endDate(new java.sql.Date(endDate.getTime()))
                               .heading(respTO.getHeading())
                               .content(respTO.getContent())
                               .type(respTO.getType().getTypeId())
                               .image(respTO.getImage() != null ? base64Util.base64ToByteArray(respTO.getImage()) : null) // 暫時先用三元式運算 因為是以postman測試
                               .deleted(respTO.getDeleted())
                               .build();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid date format", e);
        }
    }
}