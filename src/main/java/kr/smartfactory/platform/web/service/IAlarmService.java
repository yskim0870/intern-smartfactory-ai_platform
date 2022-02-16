package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.AlarmHistoryDTO;
import kr.smartfactory.platform.web.dto.AlarmSenderDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import open.commons.Result;

public interface IAlarmService {

    public Result<Boolean> insert(AlarmSenderDTO alarmSender);

    public Result<PaginationDTO<AlarmHistoryDTO>> select(String name, long startDate, long endDate, int itemCount, int pageNum, int pageItemPerPage, String order, Boolean desc);
}
