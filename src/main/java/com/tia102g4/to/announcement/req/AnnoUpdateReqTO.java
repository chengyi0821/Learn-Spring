package com.tia102g4.to.announcement.req;

import com.tia102g4.anno.to.req.AnnoReqTO;

public class AnnoUpdateReqTO extends AnnoReqTO {
	private Long id;

	public AnnoUpdateReqTO() {
	}


	public AnnoUpdateReqTO(Long id) {
		super();
		this.id = id;
	}
	

	public Long getId() {
		return id;
	}
	
	
}
