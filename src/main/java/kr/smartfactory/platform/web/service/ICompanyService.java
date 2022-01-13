package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import open.commons.Result;

/**
 * 회사 정보 Service
 * 
 * @since 2022. 1. 12. 오전 9:50:52
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 12. 오전 9:50:52 || Kyunghun Park || 최초 생성
 *
 */
public interface ICompanyService {

    public Result<PaginationDTO<CompanyInfoDTO>> selectCompany(String name, String industryType, String condition, int status, int  pageNum, int pageItemPerPage, String order, boolean desc);
}
