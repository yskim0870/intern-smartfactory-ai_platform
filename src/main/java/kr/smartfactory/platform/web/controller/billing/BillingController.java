package kr.smartfactory.platform.web.controller.billing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.smartfactory.platform.web.dto.BillingDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.impl.BillingService;
import open.commons.Result;

/**
 * 
 * @since 2022. 1. 13. 오후 4:43:31
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 13. 오후 4:43:31 || Kyunghun Park || 최초 생성
 *
 */
@RestController
public class BillingController {

    @Autowired
    private BillingService billingService;

    /**
     * (제조사, 도메인 IT 전문가) -> 과금 관리 -> 서비스 신청
     * 
     * @param request
     * @param response
     * @param billing  : 사용 시작 및 종료 일자, 분석환경 등급
     * 
     * @return : Result( 성공/실패 여부, 실패 시 메시지 )
     *
     * @since 2022. 1. 13. 오후 4:49:12
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 13. 오후 4:49:12 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/billings", method = RequestMethod.POST)
    public ResponseEntity<Result<Boolean>> serviceApply(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestBody BillingDTO billing//
    ) {
        return new ResponseEntity<>(billingService.serviceApply(billing), HttpStatus.CREATED);
    }

    /**
     * 과금 목록 조회
     * 
     * @param request
     * @param response
     * @param startDate       : 과금 신청 검색 시작 일자
     * @param endDate         : 과금 신청 검색 종료 일자
     * @param name            : 기업명
     * @param gradeName       : 환경 분석 등급명
     * @param payStatus       : 납부 상태
     * @param approvalStatus  : 승인 상태
     * @param itemCount       : 한 페이지에 보일 데이터 건수
     * @param pageNum         : 페이지 번호
     * @param pageItemPerPage : 페이지 범위
     * @param order           : 정렬 기준
     * @param desc            : 오름차순 / 내림차순
     * 
     * @return : Result( 성공/실패 여부, 실패 시 메시지, BillingDTO )
     *
     * @since 2022. 1. 21. 오후 3:08:35
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 21. 오후 3:08:35 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/billings", method = RequestMethod.GET)
    public ResponseEntity<Result<PaginationDTO<BillingDTO>>> selectBilling(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestParam(value = "startDate", defaultValue = "0", required = false) long startDate//
            , @RequestParam(value = "endDate", defaultValue = "0", required = false) long endDate//
            , @RequestParam(value = "name", required = false) @Size(max = 255) String name//
            , @RequestParam(value = "gradeName", required = false) @Size(max = 128) String gradeName//
            , @RequestParam(value = "payStatus", defaultValue = "0", required = false) int payStatus//
            , @RequestParam(value = "approvalStatus", defaultValue = "0", required = false) int approvalStatus//
            , @RequestParam(value = "itemCount", defaultValue = "0", required = false) int itemCount//
            , @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum//
            , @RequestParam(value = "pageItemPerPage", defaultValue = "0", required = false) int pageItemPerPage//
            , @RequestParam(value = "order", required = false) String order//
            , @RequestParam(value = "desc", required = false) boolean desc//
    ) {
        return new ResponseEntity<>(billingService.selectBilling(startDate, endDate, name, gradeName, payStatus, approvalStatus, itemCount, pageNum, pageItemPerPage, order, desc), HttpStatus.OK);
    }

    /**
     * 과금 상세보기
     * 
     * @param request
     * @param response
     * @param id       : 자세히 볼 과금 ID
     * 
     * @return : Result( 성공/실패 여부, 실패 시 메시지, 과금 정보, 분석환경 정보)
     *
     * @since 2022. 1. 21. 오후 3:40:54
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 21. 오후 3:40:54 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/billings/{id}", method = RequestMethod.GET)
    public ResponseEntity<Result<BillingDTO>> detailBilling(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @PathVariable String id//
    ) {
        return null;
    }

    /**
     * 과금(분석환경) 승인
     * 
     * @param request
     * @param response
     * @param id       : 승인할 과금 ID
     * 
     * @return : Result( 성공/실패 여부, 실패 시 오류 메시지 )
     *
     * @since 2022. 1. 21. 오후 3:40:56
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 21. 오후 3:40:56 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/billings/{id}", method = RequestMethod.POST)
    public ResponseEntity<Result<Boolean>> approvalBilling(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @PathVariable String id//
    ) {
        return null;
    }
}
