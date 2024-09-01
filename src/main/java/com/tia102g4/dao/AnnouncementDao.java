package com.tia102g4.dao;

import com.tia102g4.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementDao  extends JpaRepository<Announcement, Long>  {

}
