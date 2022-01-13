package kr.smartfactory.platform.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dao.ICompanyDao;
import kr.smartfactory.platform.web.dao.IEdgeGatewayDao;
import kr.smartfactory.platform.web.dao.impl.CompanyDao;
import kr.smartfactory.platform.web.dao.impl.EdgeGatewayDao;
import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.ICompanyService;
import open.commons.Result;

/**
 * 
 * @since 2022. 1. 12. 오전 9:58:05
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 12. 오전 9:58:05 || Kyunghun Park || 최초 생성
 *
 */
@Service(CompanyService.BEAN_QUALIFIER)
public class CompanyService implements ICompanyService {

    private ICompanyDao companyDao;

    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.service.impl.CompanyService";

    // Dao 생성자 주입
    @Autowired
    public CompanyService(@Qualifier(CompanyDao.BEAN_QUALIFIER) ICompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    /**
     * @see kr.smartfactory.platform.web.service.ICompanyService#selectCompany(java.lang.String,
     *      java.lang.String, java.lang.String, int, int, int, java.lang.String,
     *      boolean)
     */
    @Override
    public Result<PaginationDTO<CompanyInfoDTO>> selectCompany(String name, String industryType, String condition, int status, int pageNum, int pageItemPerPage, String order, boolean desc) {

        Result<PaginationDTO<CompanyInfoDTO>> result = new Result<>();

        PaginationDTO<CompanyInfoDTO> daoRes = new PaginationDTO<>();

        daoRes = companyDao.selectCompany(name, industryType, condition, status, pageNum, pageItemPerPage, order, desc);

        // SELECT 결과가 null이 아닌 경우
        if (daoRes != null) {
            result.setResult(true);
            result.setMessage("select success");
            result.setData(daoRes);

            return result;
        } else { // SELECT 결과가 null인 경우
            result.setResult(false);
            result.setMessage("select fail");
            result.setData(null);

            return result;
        }
    }

}
