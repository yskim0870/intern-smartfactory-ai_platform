package kr.smartfactory.platform.web.dao.model;

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
