package kr.smartfactory.platform.web.dao;

import kr.smartfactory.platform.web.dao.model.EdgeGateway;
import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;

/**
 * Edge Gateway Dao Interface
 *
 * @since 2021. 12. 28. 오후 5:55:50
 * @author "KyungHun Park"
 *
 * @modified 2021. 12. 28. 오후 5:55:50 || Kyunghun Park || 최초 생성
 *
 */
public interface IEdgeGatewayDao {

    /**
     * Edge Gateway 등록
     *
     * @param edgeGW : Gateway ID, 기업 ID, 연동 시작 일자, 연동 종료 일자
     * @return : 성공 1, 실패 0, 중복 -1
     *
     * @since 2021. 12. 28. 오후 5:57:59
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 28. 오후 5:57:59 || Kyunghun Park || 최초 생성
     *
     */
    public int createEdgeGW(EdgeGateway edgeGW);

    /**
     * EdgeGateway 목록 조회
     *
     * @param name            : 회사명
     * @param startDate       : 연동 시작 일자
     * @param endDate         : 연동 종료 일자
     * @param itemCount       : 한 페이지에 보일 데이터 건수
     * @param pageNum         : 현재 페이지 숫자
     * @param pageItemPerPage : 페이지 범위
     * @param order           : 정렬 기준
     * @param desc            : 내림차순 여부
     * @return : 조회 결과, 제조사명, 작동 여부, 최종 연동 일시, 전체 데이터 건수
     *
     * @since 2021. 12. 28. 오후 5:59:08
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 28. 오후 5:59:08 || Kyunghun Park || 최초 생성
     *
     */
    public PaginationDTO<EdgeGWDTO> selectEdgeGW(String name, long startDate, long endDate, int itemCount, int pageNum, int pageItemPerPage, String order, boolean desc);

    /**
     * EdgeGateway 상세보기
     *
     * @param id : 상세보기 할 EdgeGateway ID
     * @return : EdgeGateway 정보(최종 연동 일자, 작동 여부, IP, Port) 및 제조사 정보(제조사 ID, 제조사명,
     *         주소, 대표번호, 대표자)
     *
     * @since 2021. 12. 28. 오후 6:00:51
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 28. 오후 6:00:51 || Kyunghun Park || 최초 생성
     *
     */
    public EdgeGWDTO selectDetailEdgeGW(String id);

    /**
     * 
     *
     * @param id     : 업데이트할 Edge Gateway ID
     * @param edgeGW : 변경할 기업 ID, 연동 시작 일자, 연동 종료 일자
     * @return : 성공 1, 실패 0
     *
     * @since 2021. 12. 30. 오전 11:44:36
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 28. 오후 6:02:36 || Kyunghun Park || 최초 생성
     *
     */
    public int updateEdgeGW(EdgeGateway edgeGW);

    /**
     * 
     *
     * @param id : 삭제할 Edge Gateway ID
     * @return : 성공 1, 실패 0
     *
     * @since 2021. 12. 30. 오전 11:44:38
     * @author "KyungHun Park"
     * 
     *
     * @modified 2021. 12. 28. 오후 6:04:16 || Kyunghun Park || 최초 생성
     *
     */
    public int deleteEdgeGW(String id);

}
