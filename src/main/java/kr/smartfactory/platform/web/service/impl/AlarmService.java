package kr.smartfactory.platform.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dto.AlarmHistoryDTO;
import kr.smartfactory.platform.web.dto.AlarmSenderDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.IAlarmService;
import open.commons.Result;

/**
 * 알람내역 조회 및 등록
 * 
 * @since 2022. 2. 11. 오후 1:36:54
 * @author "KyungHun Park"
 *
 * @modified 2022. 2. 11. 오후 1:36:54 || Kyunghun Park || 최초 생성
 *
 */
@Service(AlarmService.BEAN_QUALIFIER)
public class AlarmService implements IAlarmService {

    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.service.impl.AlarmService";

    /**
     * @see kr.smartfactory.platform.web.service.IAlarmService#insert(kr.smartfactory.platform.web.dto.AlarmSenderDTO)
     */
    @Override
    public Result<Boolean> insert(AlarmSenderDTO alarmSender) {

        Result<Boolean> result = new Result<>();

        return result;
    }

    /**
     * @see kr.smartfactory.platform.web.service.IAlarmService#select(java.lang.String,
     *      long, long, int, int, int, java.lang.String, java.lang.Boolean)
     */
    @Override
    public Result<PaginationDTO<AlarmHistoryDTO>> select(String receiveId, long startDate, long endDate, int itemCount, int pageNum, int pageItemPerPage, String order, Boolean desc) {

        Result<PaginationDTO<AlarmHistoryDTO>> result = new Result<>();

        PaginationDTO<AlarmHistoryDTO> daoRes = new PaginationDTO<>();
        
        AlarmHistoryDTO alarmHistory = new AlarmHistoryDTO();
        
        alarmHistory.setId("메시지UUID");
        alarmHistory.setReceiveId("수신자 ID");
        alarmHistory.setContents("현재 신청한 입찰공고가 28일까지 마무리됩니다. 공고내용을 확인하시기 바랍니다.");
        alarmHistory.setReceiveDate(1644557990);
        alarmHistory.setChecked(0);
        
        List<AlarmHistoryDTO> list = new ArrayList<>();
        list.add(0, alarmHistory);
        
        daoRes.setItems(list);
        daoRes.setTotalCount(1);
        
        result.setResult(true);
        result.setMessage("success");
        result.setData(daoRes);

        return result;
    }

}
