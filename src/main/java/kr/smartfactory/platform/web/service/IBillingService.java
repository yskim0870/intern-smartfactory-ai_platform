package kr.smartfactory.platform.web.service;

import org.springframework.http.ResponseEntity;

import kr.smartfactory.platform.web.dto.BillingDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
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

    /**
     * 
     * @param startDate
     * @param endDate
     * @param name
     * @param gradeName
     * @param payStatus
     * @param approvalStatus
     * @param itemCount
     * @param pageNum
     * @param pageItemPerPage
     * @param order
     * @param desc
     * @return :
     *
     * @since 2022. 1. 24. 오전 10:31:15
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 24. 오전 10:31:15 || Kyunghun Park || 최초 생성
     *
     */
    public Result<PaginationDTO<BillingDTO>> select(long startDate, long endDate, String name, String gradeName, int payStatus, int approvalStatus, int itemCount, int pageNum, int pageItemPerPage, String order, boolean desc);
}
