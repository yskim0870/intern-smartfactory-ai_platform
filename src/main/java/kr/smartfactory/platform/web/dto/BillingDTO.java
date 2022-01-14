package kr.smartfactory.platform.web.dto;

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
    private String id;

    // 과금 신청일자
    private String chargeDay;

    // 납부 방법
    private String payWay;

    // 납부 상태 0: 신청, 1: 납부, 2: 미납, 3: 사용완료
    private int payStatus;

    // 사용 시작 날짜
    private long userStartDate;

    // 사용 종료 날짜
    private long userEndDate;

    // 납부 신청일
    private long payDate;

    // 일일 과금
    private long chargeDate;

    // 승인 상태 0: 대기, 1: 완료, 2: 거절
    private int approvalStatus;

    // 승인 상태 메시지
    private String approvalMessage;

    // 세금 계산서
    private int taxBillPublished;

    // 회사명, 업종
    private CompanyInfoDTO company;

    // 분석환경 등급 ID, 등급명, cpu, memory, storage, price
    private GradeEnvDTO gradeEnv;

    public BillingDTO() {

    }
}
