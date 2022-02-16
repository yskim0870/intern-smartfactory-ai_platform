package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import open.commons.Result;

/**
 * 
 *
 * @since 2021. 12. 23. 오후 5:42:24
 * @author "KyungHun Park"
 *
 * @modified 2021. 12. 23. 오후 5:42:24 || Kyunghun Park || 최초 생성
 *
 */
public interface IEdgeGatewayService {

    /**
     *
     * @param edgeGW : Gateway ID, 기업 ID, 연동 시작 일자, 연동 종료 일자를 포함한 객체
     * @return : 성공/실패 여부, 실패 메시지
     *
     * @since 2021. 12. 24. 오후 1:14:48
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오후 1:14:48 || Kyunghun Park || 최초 생성
     *
     */
    public Result<Boolean> insert(EdgeGWDTO edgeGW);

    /**
     * 
     * @param name            : 제조사명
     * @param startDate       : 연동 검색 시작 일자
     * @param endDate         : 연동 검색 종료 일자
     * @param itemCount       : 한 페이지에 보일 데이터 건수
     * @param pageNum         : 현재 페이지 번호
     * @param pageItemPerPage : 페이지 범위
     * @param order           : 정렬 기준 (option)
     * @param desc            : 내림차순 여부 (option)
     * @return : 성공/실패 여부, 실패 메시지, 조회 결과
     *
     * @since 2021. 12. 24. 오후 1:15:02
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오후 1:15:02 || Kyunghun Park || 최초 생성
     *
     */
    public Result<PaginationDTO<EdgeGWDTO>> select(String managerId, long startDate, long endDate, int itemCount, int pageNum, String order, boolean desc);

    /**
     * 
     * @param id : 상세 조회할 Edge Gateway ID
     * @return : 성공/실패 여부, 실패 메시지, 조회 결과
     *
     * @since 2021. 12. 24. 오후 1:15:06
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오후 1:15:06 || Kyunghun Park || 최초 생성
     *
     */
    public Result<EdgeGWDTO> detail(String id);

    /**
     *
     * @param id     : 수정할 Edge Gateway ID
     * @param edgeGW : 수정할 기업 ID, 연동 시작 일자, 연동 종료 일자
     * @return : 성공/실패 여부, 실패 메시지
     *
     * @since 2021. 12. 24. 오후 1:15:09
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오후 1:15:09 || Kyunghun Park || 최초 생성
     *
     */
    public Result<Boolean> update(String id, EdgeGWDTO edgeGW);

    /**
     *
     * @param id : 삭제할 Edge Gateway ID
     * @return : 성공/실패 여부, 실패 메시지
     *
     * @since 2021. 12. 24. 오후 1:15:11
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 24. 오후 1:15:11 || Kyunghun Park || 최초 생성
     *
     */
    public Result<Boolean> delete(String id);
}
