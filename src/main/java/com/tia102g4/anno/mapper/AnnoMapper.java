package com.tia102g4.anno.mapper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.tia102g4.anno.model.Anno;
import com.tia102g4.anno.to.req.AnnoReqTO;
import com.tia102g4.util.Base64Util;

import common.AnnoType;

public class AnnoMapper {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Base64Util base64Util = new Base64Util();

	// 前端進資料到後端轉給VO
	public Anno setAnno(AnnoReqTO reqTO) {
		Anno anno = new Anno();
		try {
			// 將字串轉換為java.util.Date對象
			java.util.Date startDateUtil = dateFormat.parse(reqTO.getStartDate());
			java.util.Date endDateUtil = dateFormat.parse(reqTO.getEndDate());
			// 將java.util.Date轉換為java.sql.Date
			Date startDateSql = new Date(startDateUtil.getTime());
			Date endDateSql = new Date(endDateUtil.getTime());
			anno.setStartDate(startDateSql);
			anno.setEndDate(endDateSql);
		} catch (ParseException e) {
			System.out.println("日期為空值");
		} catch (NullPointerException e) {
			System.out.println("日期為空值");
		}
		anno.setAnnoId(reqTO.getAnnoId());
		anno.setHeading(reqTO.getHeading());
		anno.setContent(reqTO.getContent());
		anno.setType(reqTO.getType().getTypeId());
		anno.setImage(base64Util.base64ToByteArray(reqTO.getImage()));
		anno.setDeleted(reqTO.getDeleted());
		return anno;
	}

	// 後端拿資料轉給TO給前端
	public AnnoReqTO setAnnoReqTO(Anno anno) {

		AnnoReqTO reqTO = new AnnoReqTO();
		reqTO.setAnnoId(anno.getAnnoId());
		reqTO.setStartDate(dateFormat.format(anno.getStartDate()));
		reqTO.setEndDate(dateFormat.format(anno.getEndDate()));
		reqTO.setHeading(anno.getHeading());
		reqTO.setContent(anno.getContent());
		reqTO.setType(AnnoType.fromTypeId(anno.getType()));
		reqTO.setImage(base64Util.byteArrayTobase64(anno.getImage()));
		reqTO.setDeleted(anno.getDeleted());
		return reqTO;
	}
}
