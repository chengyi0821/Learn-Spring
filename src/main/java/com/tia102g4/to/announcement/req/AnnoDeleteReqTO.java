package com.tia102g4.to.announcement.req;

public class AnnoDeleteReqTO {
	private Long id;
	private Boolean deleted;

	public AnnoDeleteReqTO() {

	}

	public AnnoDeleteReqTO(Long id, Boolean deleted) {
		super();
		this.id = id;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public Boolean getDeleted() {
		return deleted;
	}
}
