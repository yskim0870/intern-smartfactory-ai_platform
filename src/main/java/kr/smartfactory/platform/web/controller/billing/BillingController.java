package kr.smartfactory.platform.web.controller.billing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.smartfactory.platform.web.dto.BillingDTO;
import kr.smartfactory.platform.web.service.impl.BillingService;
import open.commons.Result;

/**
 * 
 * @since 2022. 1. 13. 오후 4:43:31
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 13. 오후 4:43:31 || Kyunghun Park || 최초 생성
 *
 */
@RestController
public class BillingController {

    @Autowired
    private BillingService billingService;
    
    /**
     * 과금 관리 -> 서비스 신청
     * 
     * @param request
     * @param response
     * @param billing
     * @return :
     *
     * @since 2022. 1. 13. 오후 4:49:12
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 13. 오후 4:49:12 || Kyunghun Park || 최초 생성
     *
     */
    public ResponseEntity<Result<Boolean>> serviceApply(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestBody BillingDTO billing) {
        return new ResponseEntity<>(billingService.serviceApply(billing), HttpStatus.CREATED);
    }
}
