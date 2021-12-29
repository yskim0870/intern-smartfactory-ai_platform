package kr.smartfactory.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.impl.EdgeGatewayService;

@RestController
public class EdgeGatewayController {

    @Autowired
    private EdgeGatewayService edgeGWService;

    /**
     * Edge Gateway 등록
     *
     * @param edgeGW : Gateway ID, 기업 ID, 연동 시작 일자, 연동 종료 일자를 포함한 객체
     * @return :
     *
     * @since 2021. 12. 24. 오전 11:20:34
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오전 11:20:34 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/edge-gws", method = RequestMethod.POST)
    public ResponseEntity<Object> createEdgeGW(@RequestBody EdgeGWDTO edgeGW) {

        return new ResponseEntity<>(edgeGWService.createEdgeGW(edgeGW), HttpStatus.CREATED);
    }

    /**
     * Edge Gateway 조회
     *
     * @param name            : 제조사명
     * @param startDate       : 연동 검색 시작 일자
     * @param endDate         : 연동 검색 종료 일자
     * @param itemCount       : 한 페이지에 보일 데이터 건수
     * @param pageNum         : 현재 페이지 번호
     * @param pageItemPerPage : 페이지 범위
     * @param order           : 정렬 기준 (option)
     * @param desc            : 내림차순 여부 (option)
     * @return :
     *
     * @since 2021. 12. 24. 오전 11:20:48
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오전 11:20:48 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/edge-gws", method = RequestMethod.GET)
    public ResponseEntity<PaginationDTO<EdgeGWDTO>> selectEdgeGW(//
            @RequestParam(value = "managerId", required = false) String managerId//
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
     * @param id : 상세 조회할 Edge Gateway ID
     * @return :
     *
     * @since 2021. 12. 24. 오전 11:20:58
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오전 11:20:58 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/edge-gws/{id}", method = RequestMethod.GET)
    public ResponseEntity<EdgeGWDTO> selectDetailEdgeGW(@PathVariable("id") String id) {

        return new ResponseEntity<>(edgeGWService.selectDetailEdgeGW(id), HttpStatus.OK);
    }

    /**
     * Edge Gateway 수정
     *
     * @param id     : 수정할 Edge Gateway ID
     * @param edgeGW : 기업 ID, 연동 시작 일자, 연동 종료 일자
     * @return :
     *
     * @since 2021. 12. 24. 오전 11:21:10
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오전 11:21:10 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/edge-gws/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Integer> updateEdgeGW(@PathVariable("id") String id, @RequestBody EdgeGWDTO edgeGW) {

        return new ResponseEntity<>(edgeGWService.updateEdgeGW(id, edgeGW), HttpStatus.OK);
    }

    /**
     * Edge Gateway 삭제
     *
     * @param id : 삭제할 Edge Gateway ID
     * @return :
     *
     * @since 2021. 12. 24. 오전 11:21:18
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오전 11:21:18 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/edge-gws/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteEdgeGW(@PathVariable("id") String id) {

        return new ResponseEntity<>(edgeGWService.deleteEdgeGW(id), HttpStatus.OK);
    }
}
