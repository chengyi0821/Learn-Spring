package com.tia102g4.anno.to.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
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
