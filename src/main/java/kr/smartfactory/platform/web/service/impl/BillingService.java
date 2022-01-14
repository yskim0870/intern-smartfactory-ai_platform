package kr.smartfactory.platform.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dao.IBillingDao;
import kr.smartfactory.platform.web.dao.impl.BillingDao;
import kr.smartfactory.platform.web.dto.BillingDTO;
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

}
