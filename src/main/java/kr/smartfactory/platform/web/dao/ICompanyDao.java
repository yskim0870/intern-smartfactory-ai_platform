package kr.smartfactory.platform.web.dao;

import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;

/**
 * 
 * @since 2022. 1. 12. 오전 9:57:59
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 12. 오전 9:57:59 || Kyunghun Park || 최초 생성
 *
 */
public interface ICompanyDao {

    public PaginationDTO<CompanyInfoDTO> selectCompany(String name, String industryType, String condition, int status, int pageNum, int pageItemPerPage, String order, boolean desc);
}
