package kr.smartfactory.platform.web.dto;

import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

/**
 * Billing Data Transfer Object
 * 
 * @since 2022. 1. 14. 오전 8:58:24
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 14. 오전 8:58:24 || Kyunghun Park || 최초 생성
 *
 */
public class BillingDTO {

	// 과금 UUID
	private String id = "과금 UUID";

	// 과금 신청일자
	private long chargeDate = 0000;

	// 납부 방법
	private String payMethod = "납부 방법";

	// 납부 상태 0: 신청, 1: 납부, 2: 미납, 3: 사용완료
	private int payStatus = 0;

	// 사용 시작 날짜
	private long useStartDate = 20160749;

	// 사용 종료 날짜
	private long useEndDate = 20160749;

	// 납부 신청일
	private long payDate = 20160750;

	// 일일 과금
	private long dailyBilling = 20160751;

	// 승인 상태 0: 대기, 1: 완료, 2: 거절
	private int approvalStatus = 2;

	// 승인 상태 메시지
	private String approvalMessage = "승인";

	// 세금 계산서
	private int taxBillPublished = 60;

	// 회사명, 업종
	private CompanyInfoDTO company;

	// 분석환경 등급 ID, 등급명, cpu, memory, storage, price
	private GradeEnvDTO gradeEnv;

	public BillingDTO() {

	}

	/**
	 * id을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:56
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * id 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:56
	 * @author "KyungHun Park"
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * chargeDate을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public long getChargeDate() {
		return chargeDate;
	}

	/**
	 * chargeDate 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param chargeDate
	 */
	public void setChargeDate(long chargeDate) {
		this.chargeDate = chargeDate;
	}

	/**
	 * payWay을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * payWay 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param payWay
	 */
	public void setPayMethod(String payWay) {
		this.payMethod = payWay;
	}

	/**
	 * payStatus을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public int getPayStatus() {
		return payStatus;
	}

	/**
	 * payStatus 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param payStatus
	 */
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * userStartDate을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public long getUseStartDate() {
		return useStartDate;
	}

	/**
	 * userStartDate 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param userStartDate
	 */
	public void setUseStartDate(long userStartDate) {
		this.useStartDate = userStartDate;
	}

	/**
	 * userEndDate을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public long getUseEndDate() {
		return useEndDate;
	}

	/**
	 * userEndDate 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param userEndDate
	 */
	public void setUseEndDate(long userEndDate) {
		this.useEndDate = userEndDate;
	}

	/**
	 * payDate을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public long getPayDate() {
		return payDate;
	}

	/**
	 * payDate 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param payDate
	 */
	public void setPayDate(long payDate) {
		this.payDate = payDate;
	}

	/**
	 * dailyBilling을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public long getDailyBilling() {
		return dailyBilling;
	}

	/**
	 * dailyBilling 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param dailyBilling
	 */
	public void setDailyBilling(long dailyBilling) {
		this.dailyBilling = dailyBilling;
	}

	/**
	 * approvalStatus을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public int getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatus 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param approvalStatus
	 */
	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * approvalMessage을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getApprovalMessage() {
		return approvalMessage;
	}

	/**
	 * approvalMessage 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param approvalMessage
	 */
	public void setApprovalMessage(String approvalMessage) {
		this.approvalMessage = approvalMessage;
	}

	/**
	 * taxBillPublished을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public int getTaxBillPublished() {
		return taxBillPublished;
	}

	/**
	 * taxBillPublished 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param taxBillPublished
	 */
	public void setTaxBillPublished(int taxBillPublished) {
		this.taxBillPublished = taxBillPublished;
	}

	/**
	 * company을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public CompanyInfoDTO getCompany() {
		return company;
	}

	/**
	 * company 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param company
	 */
	public void setCompany(CompanyInfoDTO company) {
		this.company = company;
	}

	/**
	 * gradeEnv을(를) 호출합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public GradeEnvDTO getGradeEnv() {
		return gradeEnv;
	}

	/**
	 * gradeEnv 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오후 12:43:57
	 * @author "KyungHun Park"
	 *
	 * @param gradeEnv
	 */
	public void setGradeEnv(GradeEnvDTO gradeEnv) {
		this.gradeEnv = gradeEnv;
	}
}
