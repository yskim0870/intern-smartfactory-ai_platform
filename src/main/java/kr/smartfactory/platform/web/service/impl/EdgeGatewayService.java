package kr.smartfactory.platform.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dao.IEdgeGatewayDao;
import kr.smartfactory.platform.web.dao.impl.EdgeGatewayDao;
import kr.smartfactory.platform.web.dao.model.EdgeGateway;
import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.IEdgeGatewayService;
import open.commons.Result;

/**
 * 
 *
 * @since 2021. 12. 23. 오후 5:42:30
 * @author "KyungHun Park"
 *
 * @modified 2021. 12. 23. 오후 5:42:30 || Kyunghun Park || 최초 생성
 *
 */
@Service(EdgeGatewayService.BEAN_QUALIFIER)
public class EdgeGatewayService implements IEdgeGatewayService {

    private IEdgeGatewayDao edgeGWDao;
    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.service.impl.EdgeGatewayService";

    // Dao 생성자 주입
    @Autowired
    public EdgeGatewayService(@Qualifier(EdgeGatewayDao.BEAN_QUALIFIER) IEdgeGatewayDao edgeGWDao) {
        this.edgeGWDao = edgeGWDao;
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#createEdgeGW(kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public Result<Boolean> createEdgeGW(EdgeGWDTO edgeGWDTO) {

        // 결과, 메시지를 담을 객체
        Result<Boolean> result = new Result<>();

        EdgeGateway edgeGW = new EdgeGateway(edgeGWDTO.getId(), edgeGWDTO.getManagerId(), edgeGWDTO.getStartDate(), edgeGWDTO.getEndDate());

        // POST 성공할 경우
        if (this.edgeGWDao.createEdgeGW(edgeGW) != 0) {
            result.setResult(true);
            result.setMessage("create success");

            return result;
        } else { // POST 실패할 경우
            result.setResult(false);
            result.setMessage("create fail");

            return result;
        }
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#selectEdgeGW(java.lang.String,
     *      long, long, int, int, int, String, boolean)
     */
    @Override
    public Result<PaginationDTO<EdgeGWDTO>> selectEdgeGW(String managerId, long startDate, long endDate, int itemCount, int pageNum, int pageItemPerPage, String order, boolean desc) {

        // 결과, 메시지, 데이터를 담을 객체
        Result<PaginationDTO<EdgeGWDTO>> result = new Result<>();

        // EdgeGWDTO와 총 데이터 건수를 담은 객체
        PaginationDTO<EdgeGWDTO> res = new PaginationDTO<>();

        // SELECT 실행
            res = edgeGWDao.selectEdgeGW(managerId, startDate, endDate, itemCount, pageNum, pageItemPerPage, order, desc);

        // SELECT 결과가 null이 아닌 경우
        if (res != null) {
            result.setResult(true);
            result.setMessage("select success");
            result.setData(res);

            return result;
        } else { // SELECT 결과가 null인 경우
            result.setResult(false);
            result.setMessage("select fail");
            result.setData(null);

            return result;
        }
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#selectDetailEdgeGW(java.lang.String)
     */
    @Override
    public Result<EdgeGWDTO> selectDetailEdgeGW(String id) {

        // 결과, 메시지, 데이터를 담을 객체
        Result<EdgeGWDTO> result = new Result<>();

        // EdgeGateway 정보(최종 연동 일자, 작동 여부, IP, Port) 및 제조사 정보(제조사 ID, 제조사명, 주소, 대표번호,
        // 대표자)를 담을 객체
        EdgeGWDTO res = new EdgeGWDTO();

        // SELECT 실행
        res = edgeGWDao.selectDetailEdgeGW(id);

        // SELECT 결과가 null이 아닌 경우
        if (res != null) {
            result.setResult(true);
            result.setMessage("detail success");
            result.setData(res);

            return result;
        } else { // SELECT 결과가 NULL일 경우
            result.setResult(false);
            result.setMessage("detail fail");
            result.setData(null);

            return result;
        }

    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#updateEdgeGW(java.lang.String,
     *      kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public Result<Boolean> updateEdgeGW(String id, EdgeGWDTO edgeGWDTO) {

        EdgeGateway edgeGW = new EdgeGateway(id, edgeGWDTO.getManagerId(), edgeGWDTO.getStartDate(), edgeGWDTO.getEndDate());

        // 결과, 메시지를 담을 객체
        Result<Boolean> result = new Result<>();

        // PATCH된 row가 있을 경우
        if (edgeGWDao.updateEdgeGW(edgeGW) != 0) {
            result.setResult(true);
            result.setMessage("update success");

            return result;
        } else { // PATCH된 row가 없을 경우
            result.setResult(false);
            result.setMessage("update fail");

            return result;
        }
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#deleteEdgeGW(java.lang.String)
     */
    @Override
    public Result<Boolean> deleteEdgeGW(String id) {

        // 결과, 메시지를 담을 객체
        Result<Boolean> result = new Result<>();

        // DELETE에 성공한 경우
        if (edgeGWDao.deleteEdgeGW(id) != 0) {
            result.setResult(true);
            result.setMessage("delete success");

            return result;
        } else { // DELETE에 실패한 경우
            result.setResult(false);
            result.setMessage("delete fail");

            return result;
        }
    }

}
