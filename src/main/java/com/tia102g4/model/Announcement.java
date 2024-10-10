package com.tia102g4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.beans.JavaBean;
import java.sql.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anno_id", updatable = false)
    private Long annoId;	//公告編號

    @Column(name = "start_date")
    private Date startDate;	//起始日期

    @Column(name = "end_date")
    private Date endDate;	//結束日期

    @Column(name = "heading")
    private String heading;	//主旨

    @Column(name = "content")
    private String content;	//內容

    @Column(name = "type")
    private Integer type;	//類型

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private Byte[] image;	//圖片

    @Column(name = "deleted")
    private Boolean deleted; //刪除狀態

}
