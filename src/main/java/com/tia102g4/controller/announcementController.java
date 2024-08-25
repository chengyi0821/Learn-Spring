package com.tia102g4.controller;

import com.google.gson.JsonObject;
import com.tia102g4.common.response.PageResponse;
import com.tia102g4.service.AnnouncementService;
import com.tia102g4.to.announcement.req.AnnouncementReqTO;
import com.tia102g4.to.announcement.resp.AnnouncementRespTO;
import com.tia102g4.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcement")
public class announcementController {

    private final AnnouncementService announcementService;

//    private final BaseResponse baseResponse;

    @PostMapping("/console")
    public ResponseEntity<String> insert(@RequestBody @Validated AnnouncementReqTO reqTO) {
        announcementService.create(reqTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("新增成功");
    }

    @PutMapping("/console")
    public void update(String requestBody) {
//        AnnoUpdateReqTO reqTO = gson.fromJson(requestBody, AnnoUpdateReqTO.class);
//        annoService.update(reqTO);
    }

    @DeleteMapping("/console")
    private void delete(String requestBody) {
//        AnnoDeleteReqTO reqTO = gson.fromJson(requestBody, AnnoDeleteReqTO.class);
//        annoService.delete(reqTO);
    }

    @GetMapping("/console/{page}/{count}")
    public PageResponse<AnnouncementRespTO> getByPage(@PathVariable Long page, @PathVariable Long count) {
        return announcementService.getByPageAndCount(page, count);
    }

    @GetMapping
    public void getAllForMember(HttpServletRequest req) {
//        List<AnnoReqTO> reqTOList = annoService.getAllAnnos();
//        return baseResponse.jsonResponse(reqTOList);
    }

    @GetMapping("console/query")
    public void getCompositeAnnosQuery(HttpServletRequest req) {
        Map<String, String[]> map = req.getParameterMap();

        // 如果map沒資料就回傳空值
        if (map == null) {
//            return null;
        }
//        List<AnnoReqTO> reqTOList = annoService.getAnnosByCompositeQuery(map);
//        return baseResponse.jsonResponse(reqTOList);
    }

}
