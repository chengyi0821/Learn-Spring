package com.tia102g4.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tia102g4.anno.to.req.AnnoDeleteReqTO;
import com.tia102g4.common.response.PageResponse;
import com.tia102g4.service.AnnouncementService;
import com.tia102g4.to.announcement.resp.AnnouncementRespTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcement")
public class announcementController {

    private final AnnouncementService announcementService;

    @PostMapping("/console")
    public ResponseEntity<String> saveOrUpdate(@RequestBody @Validated AnnouncementRespTO reqTO) {
        announcementService.saveOrUpdate(reqTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("新增或修改成功");
    }

    @DeleteMapping("/console")
    private void delete(@RequestBody AnnoDeleteReqTO reqTO) {
    	announcementService.delete(reqTO);
    }

    @GetMapping("/console/{page}/{pageSize}")
    public PageResponse<AnnouncementRespTO> getByPage(@PathVariable Integer page,
    												  @PathVariable Integer pageSize){
        return announcementService.getByPageAndCount(page, pageSize);
    }

    @PostMapping("console/query")
    public List<AnnouncementRespTO> getCompositeAnnosQuery(@RequestBody Map<String, String> map) {
        try {
            return announcementService.getAnnosByCompositeQuery(map);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
