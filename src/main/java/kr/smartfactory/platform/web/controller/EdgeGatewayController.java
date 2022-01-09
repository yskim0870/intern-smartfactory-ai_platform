package kr.smartfactory.platform.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.impl.EdgeGatewayService;
import open.commons.Result;

/**
 * Edge Gateway CRUD Controller
 *
 * @since 2021. 12. 24. 오전 9:19:17
 * @author "KyungHun Park"
 *
 * @modified 2021. 12. 24. 오전 9:19:17 || Kyunghun Park || 최초 생성
 *
 */
@Validated
@RestController
public class EdgeGatewayController {

    @Autowired
    private EdgeGatewayService edgeGWService;

    /**
     * Edge Gateway 등록
     *
     * @param request  : request 정보를 servlet에 전달
     * @param response : HTTP 응답 코드를 servlet에 전달
     * @param edgeGW   : Gateway ID, 기업 ID, 연동 시작 일자, 연동 종료 일자를 포함한 객체
     * @return : 성공/실패 여부, 실패 시 메시지, httpStatus
     *
     * @since 2021. 12. 24. 오전 11:20:34
     * @author "KyungHun Park"
     * 
     * @modified 2021. 12. 30. 오전 11:10:11 || Kyunghun Park || Servlet 추가
     */
    @RequestMapping(value = "/edge-gws", method = RequestMethod.POST)
    public ResponseEntity<Result<Boolean>> createEdgeGW(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestBody EdgeGWDTO edgeGW) {

        return new ResponseEntity<>(edgeGWService.createEdgeGW(edgeGW), HttpStatus.CREATED);
    }

    /**
     * Edge Gateway 조회
     *
     * @param request         : request 정보를 servlet에 전달
     * @param response        : HTTP 응답 코드를 servlet에 전달
     * @param name            : 제조사명
     * @param startDate       : 연동 검색 시작 일자
     * @param endDate         : 연동 검색 종료 일자
     * @param itemCount       : 한 페이지에 보일 데이터 건수
     * @param pageNum         : 현재 페이지 번호
     * @param pageItemPerPage : 페이지 범위
     * @param order           : 정렬 기준 (option)
     * @param desc            : 내림차순 여부 (option)
     * @return : 성공/실패 여부, 실패 시 메시지, 조회 결과, httpStatus S
     * @since 2021. 12. 24. 오전 11:20:48
     * @author "KyungHun Park"
     *
     */
    @RequestMapping(value = "/edge-gws", method = RequestMethod.GET)
    public ResponseEntity<Result<PaginationDTO<EdgeGWDTO>>> selectEdgeGW(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestParam(value = "managerId", required = false) @Size(max = 36) String managerId//
            , @RequestParam(value = "startDate", defaultValue = "0", required = false) long startDate//
            , @RequestParam(value = "endDate", defaultValue = "0", required = false) long endDate//
            , @RequestParam(value = "itemCount", defaultValue = "0", required = false) int itemCount//
            , @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum//
            , @RequestParam(value = "pageItemPerPage", defaultValue = "0", required = false) int pageItemPerPage//
            , @RequestParam(value = "order", required = false) String order//
            , @RequestParam(value = "desc", required = false) boolean desc) {
        return new ResponseEntity<>(edgeGWService.selectEdgeGW(managerId, startDate, endDate, itemCount, pageNum, pageItemPerPage, order, desc), HttpStatus.OK);
    }

    /**
     * Edge Gateway 상세 조회
     *
     * @param request  : request 정보를 servlet에 전달
     * @param response : HTTP 응답 코드를 servlet에 전달
     * @param id       : 상세 조회할 Edge Gateway ID
     * @return : 성공/실패 여부, 실패 시 메시지, 조회 결과, httpStatus
     *
     * @since 2021. 12. 24. 오전 11:20:58
     * @author "KyungHun Park"
     * 
     * @modified 2021. 12. 24. 오전 11:20:58 || Kyunghun Park || 최초 생성
     */
    @RequestMapping(value = "/edge-gws/{id}", method = RequestMethod.GET)
    public ResponseEntity<Result<EdgeGWDTO>> selectDetailEdgeGW(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @PathVariable("id") String id) {

        return new ResponseEntity<>(edgeGWService.selectDetailEdgeGW(id), HttpStatus.OK);
    }

    /**
     * Edge Gateway 수정
     *
     * @param request  : request 정보를 servlet에 전달
     * @param response : HTTP 응답 코드를 servlet에 전달
     * @param id       : 수정할 Edge Gateway ID
     * @param edgeGW   : 기업 ID, 연동 시작 일자, 연동 종료 일자
     * @return : 성공/실패 여부, 실패 시 메시지, httpStatus
     *
     * @since 2021. 12. 24. 오전 11:21:10
     * @author "KyungHun Park"
     * 
     * @modified 2021. 12. 24. 오전 11:21:10 || Kyunghun Park || 최초 생성
     */
    @RequestMapping(value = "/edge-gws/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Result<Boolean>> updateEdgeGW(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @PathVariable("id") String id//
            , @RequestBody EdgeGWDTO edgeGW) {

        return new ResponseEntity<>(edgeGWService.updateEdgeGW(id, edgeGW), HttpStatus.OK);
    }

    /**
     * Edge Gateway 삭제
     *
     * @param request  : request 정보를 servlet에 전달
     * @param response : HTTP 응답 코드를 servlet에 전달
     * @param id       : 삭제할 Edge Gateway ID
     * @return : 성공/실패 여부, 실패 시 메시지, httpStatus
     *
     * @since 2021. 12. 24. 오전 11:21:18
     * @author "KyungHun Park"
     * 
     * @modified 2021. 12. 24. 오전 11:21:18 || Kyunghun Park || 최초 생성
     */
    @RequestMapping(value = "/edge-gws/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Result<Boolean>> deleteEdgeGW(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @PathVariable("id") String id) {

        return new ResponseEntity<>(edgeGWService.deleteEdgeGW(id), HttpStatus.OK);
    }
}
