package kr.smartfactory.platform.web.dto;

import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

public class AlarmHistoryDTO {
	private String id;
	private String receivedId;
	private String contents;
	private long receiveDate;
	private int checked;
	private CompanyInfoDTO company;

	public AlarmHistoryDTO() {

	}
}
