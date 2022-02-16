package kr.smartfactory.platform.web.controller.alarm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.smartfactory.platform.web.dto.AlarmHistoryDTO;
import kr.smartfactory.platform.web.dto.AlarmSenderDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.impl.AlarmService;
import open.commons.Result;

/**
 * 
 * @since 2022. 2. 14. 오후 4:10:59
 * @author "KyungHun Park"
 *
 * @modified 2022. 2. 14. 오후 4:10:59 || Kyunghun Park || 최초 생성
 *
 */
@RestController
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    /**
     * 알람 관리 -> 알람 예약/전송 기능
     * 
     * @param request  : request 정보를 서블렛에 전달
     * @param response : HTTP 응답 코드를 서블렛에 전달
     * @param alarm    : AlarmSenderDTO( 기업 ID, 예약 발송 여부, 예약 전송 일자, 알람 내용)
     * @return : 성공/실패 여부, 메시지
     *
     * @since 2022. 2. 14. 오후 4:23:59
     * @author "KyungHun Park"
     * 
     * @modified 2022. 2. 14. 오후 4:23:59 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/alarms", method = RequestMethod.PUT)
    public ResponseEntity<Result<Boolean>> insert(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestBody AlarmSenderDTO alarm) {

        return new ResponseEntity<>(alarmService.insert(alarm), HttpStatus.CREATED);
    }

    /**
     * 알람 관리 -> 알람 내역 조회
     * 
     * @param request         : request 정보를 서블렛에 전달
     * @param response        : HTTP 응답 코드를 서블렛에 전달
     * @param receiveId       : 기업명
     * @param startDate       : 알람 검색 시작 일자
     * @param endDate         : 알람 검색 종료 일자
     * @param itemCount       : 한 페이지 데이터 건수
     * @param pageNum         : 현재 페이지 번호
     * @param pageItemPerPage : 페이지 범위
     * @param order           : 정렬 기준
     * @param desc            : 내림차순
     * @return : 성공/실패 여부, 메시지, PagainationDTO( AlarmHistoryDTO )
     *
     * @since 2022. 2. 14. 오후 4:32:06
     * @author "KyungHun Park"
     * 
     * @modified 2022. 2. 14. 오후 4:32:06 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/alarms", method = RequestMethod.GET)
    public ResponseEntity<Result<PaginationDTO<AlarmHistoryDTO>>> select(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestParam(value = "receiveId", required = false) @Size(max = 36) String receiveId//
            , @RequestParam(value = "startDate", defaultValue = "0", required = false) long startDate//
            , @RequestParam(value = "endDate", defaultValue = "0", required = false) long endDate//
            , @RequestParam(value = "itemCount", defaultValue = "0", required = false) int itemCount//
            , @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum//
            , @RequestParam(value = "endpageItemPerPage", defaultValue = "0", required = false) int pageItemPerPage//
            , @RequestParam(value = "order", required = false) @Size(max = 36) String order//
            , @RequestParam(value = "desc", required = false) boolean desc//

    ) {

        return new ResponseEntity<>(alarmService.select(receiveId, startDate, endDate, itemCount, pageNum, pageItemPerPage, order, desc), HttpStatus.OK);
    }
}
