package kr.smartfactory.platform.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dao.IEdgeGatewayDao;
import kr.smartfactory.platform.web.dao.impl.EdgeGatewayDao;
import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.IEdgeGatewayService;

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
    public static final String BEAN_QUALIFIER ="kr.smartfactory.platform.web.service.impl.EdgeGatewayService";
    
    @Autowired
    public EdgeGatewayService(@Qualifier(EdgeGatewayDao.BEAN_QUALIFIER) IEdgeGatewayDao edgeGWDao) {
        this.edgeGWDao = edgeGWDao;
    }
    

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#createEdgeGW(kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public int createEdgeGW(EdgeGWDTO edgeGW) {

        if(edgeGWDao.createEdgeGW(edgeGW) == 0) {
            
        }
        
        return edgeGWDao.createEdgeGW(edgeGW);
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#selectEdgeGW(java.lang.String,
     *      long, long, int, int, int, String, boolean)
     */
    @Override
    public PaginationDTO<EdgeGWDTO> selectEdgeGW(String managerId, long startDate, long endDate, int itemCount, int pageNum, int pageItemPerPage, String order, boolean desc) {
        PaginationDTO<EdgeGWDTO> res = new PaginationDTO<>();
        res = (PaginationDTO<EdgeGWDTO>) edgeGWDao.selectEdgeGW(managerId, startDate, endDate, itemCount, pageNum, pageItemPerPage, order, desc);
        
        return res;
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#selectDetailEdgeGW(java.lang.String)
     */
    @Override
    public EdgeGWDTO selectDetailEdgeGW(String id) {

        return edgeGWDao.selectDetailEdgeGW(id);
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#updateEdgeGW(java.lang.String,
     *      kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public int updateEdgeGW(String id, EdgeGWDTO edgeGW) {

        return edgeGWDao.updateEdgeGW(id, edgeGW);
    }

    /**
     * @see kr.smartfactory.platform.web.service.IEdgeGatewayService#deleteEdgeGW(java.lang.String)
     */
    @Override
    public int deleteEdgeGW(String id) {

        return edgeGWDao.deleteEdgeGW(id);
    }

}
