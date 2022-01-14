package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.BillingDTO;
import open.commons.Result;

/**
 * 과금 관리 Service 단
 * 
 * @since 2022. 1. 13. 오후 4:48:44
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 13. 오후 4:48:44 || Kyunghun Park || 최초 생성
 *
 */
public interface IBillingService {

    /**
     * 서비스 신청
     * 
     * @param billing
     * @return :
     *
     * @since 2022. 1. 13. 오후 4:49:01
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 13. 오후 4:49:01 || Kyunghun Park || 최초 생성
     *
     */
    public Result<Boolean> serviceApply(BillingDTO billing);
}
