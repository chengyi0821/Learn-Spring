package com.tia102g4.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.model.Announcement;

public interface AnnouncementDaoCustom {
	List<Announcement> findByCompositeQuery(Map<String, String> map);
}
