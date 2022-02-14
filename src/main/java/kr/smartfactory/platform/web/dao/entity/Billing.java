package kr.smartfactory.platform.web.dao.entity;

/**
 * 
 * @since 2022. 1. 14. 오전 8:53:26
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 14. 오전 8:53:26 || Kyunghun Park || 최초 생성
 *
 */
public class Billing {
    
    // 과금 UUID
    private String id;
    
    // 과금 신청일자
    private long chargeDate;
    
    // 신청자 ID, 사용자 ID
    private int userID;
    
    // 분석환경 등급 ID
    private int envGradeID;
    
    /**
     * id을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
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
     * @since 2022. 1. 24. 오전 10:57:37
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
     * @since 2022. 1. 24. 오전 10:57:37
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
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param chargeDate
     */
    public void setChargeDate(long chargeDate) {
        this.chargeDate = chargeDate;
    }

    /**
     * userID을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getUserID() {
        return userID;
    }

    /**
     * userID 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * envGradeID을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getEnvGradeID() {
        return envGradeID;
    }

    /**
     * envGradeID 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param envGradeID
     */
    public void setEnvGradeID(int envGradeID) {
        this.envGradeID = envGradeID;
    }

    /**
     * startDate을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public long getStartDate() {
        return StartDate;
    }

    /**
     * startDate 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param startDate
     */
    public void setStartDate(long startDate) {
        StartDate = startDate;
    }

    /**
     * endDate을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * endDate 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param endDate
     */
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    /**
     * paymentMethod을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * paymentMethod 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * paymentDate을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public long getPaymentDate() {
        return paymentDate;
    }

    /**
     * paymentDate 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param paymentDate
     */
    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * taxBillpublished을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getTaxBillpublished() {
        return taxBillpublished;
    }

    /**
     * taxBillpublished 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param taxBillpublished
     */
    public void setTaxBillpublished(int taxBillpublished) {
        this.taxBillpublished = taxBillpublished;
    }

    /**
     * status을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getStatus() {
        return status;
    }

    /**
     * status 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * approvalStatus을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
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
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @param approvalStatus
     */
    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * approvalMsg을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오전 10:57:37
     * @author "KyungHun Park"
     *
     * @return 
     */
    public String getApprovalMsg() {
        return approvalMsg;
    }

    /**
	 * approvalMsg 을(를) 지정합니다.
	 *
	 * @since 2022. 1. 24. 오전 10:57:37
	 * @author "KyungHun Park"
	 *
	 * @param approvalMsg
	 */
    public void setApprovalMsg(String approvalMsg) {
        this.approvalMsg = approvalMsg;
    }

    // 사용 시작 날짜
    private long StartDate;
    
    // 사용 종료 날짜
    private long endDate;
    
    // 납부 방법
    private String paymentMethod;
    
    // 납부 일자
    private long paymentDate;
    
    // 세금 계산서 발행 여부 0: 미발행, 1: 발행
    private int taxBillpublished;
    
    // 납부 상태 0 : 신청, 1: 납부, 2: 미납, 3:사용 완료
    private int status;
    
    // 승인 상태 0: 대기, 1: 완료, 2: 거절
    private int approvalStatus;
    
    // 승인 상태에 대한 메시지
    private String approvalMsg;
}
