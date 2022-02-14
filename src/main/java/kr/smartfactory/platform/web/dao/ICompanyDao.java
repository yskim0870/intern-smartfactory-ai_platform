package kr.smartfactory.platform.web.dao;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

/**
 * 
 * @since 2022. 1. 12. 오전 9:57:59
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 12. 오전 9:57:59 || Kyunghun Park || 최초 생성
 *
 */
public interface ICompanyDao {

    /**
     * 회사 목록 조회
     * @param name
     * @param industryType
     * @param condition
     * @param status
     * @param pageNum
     * @param pageItemPerPage
     * @param order
     * @param desc
     * @return :
     *
     * @since 2022. 1. 14. 오전 8:57:23
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 14. 오전 8:57:23 || Kyunghun Park || 최초 생성
     *
     */
    public PaginationDTO<CompanyInfoDTO> selectCompany(String name, String industryType, String condition, int status, int pageNum, int pageItemPerPage, String order, boolean desc);
}
