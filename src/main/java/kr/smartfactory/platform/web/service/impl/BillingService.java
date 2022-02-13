package kr.smartfactory.platform.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dao.IBillingDao;
import kr.smartfactory.platform.web.dao.impl.BillingDao;
import kr.smartfactory.platform.web.dao.model.Billing;
import kr.smartfactory.platform.web.dto.BillingDTO;
import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.GradeEnvDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.IBillingService;
import open.commons.Result;

/**
 * 
 * @since 2022. 1. 14. 오전 8:53:38
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 14. 오전 8:53:38 || Kyunghun Park || 최초 생성
 *
 */
@Service(BillingService.BEAN_QUALIFIER)
public class BillingService implements IBillingService {

    private IBillingDao billingDao;
    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.service.impl.BillingService";

    @Autowired
    public BillingService(@Qualifier(BillingDao.BEAN_QUALIFIER) IBillingDao billingDao) {
        this.billingDao = billingDao;
    }

    /**
     * @see kr.smartfactory.platform.web.service.IBillingService#serviceApply(kr.smartfactory.platform.web.dto.BillingDTO)
     */
    @Override
    public Result<Boolean> serviceApply(BillingDTO billing) {

        Result<Boolean> result = new Result<>();

        return null;
    }

    /**
     * @see kr.smartfactory.platform.web.service.IBillingService#selectBilling(long,
     *      long, java.lang.String, java.lang.String, int, int, int, int, int,
     *      java.lang.String, boolean)
     */
    public Result<PaginationDTO<BillingDTO>> selectBilling(long startDate, long endDate, String name, String gradeName, int payStatus, int approvalStatus, int itemCount, int pageNum, int pageItemPerPage, String order, boolean desc) {

        Result<PaginationDTO<BillingDTO>> result = new Result<>();
        
        PaginationDTO<BillingDTO> daoRes = new PaginationDTO<>();
        
        BillingDTO billing = new BillingDTO();
        
        List<BillingDTO> list = new ArrayList<>();
        
        GradeEnvDTO grade = new GradeEnvDTO();
        
        CompanyInfoDTO company = new CompanyInfoDTO();
        
        grade.setId(0);
        grade.setPrice(30000);
        grade.setName("Standard");
        grade.setCpu(2);
        grade.setMemory(2048);
        grade.setStorage(512);
        
        company.setIndustryType("제조사");
        company.setName("(주)유미테크");
        
        billing.setGradeEnv(grade);
        billing.setCompany(company);
        
        
        billing.setId("사용자 분류 ID");
        billing.setChargeDate(00000);
        billing.setPayStatus(0);
        billing.setApprovalStatus(1);
        billing.setUseStartDate(0);
        billing.setUseEndDate(3000);
        billing.setPayDate(10000);
        billing.setTaxBillPublished(1);
        list.add(0, billing);
        
        
        daoRes.setItems(list);
        
        result.setResult(true);
        result.setMessage("dd");
        result.setData(daoRes);
        
        return result;
    }

}
