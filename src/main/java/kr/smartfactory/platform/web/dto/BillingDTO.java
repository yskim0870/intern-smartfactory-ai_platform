package kr.smartfactory.platform.web.dto;

public class BillingDTO {
    private String id;
    private String chargeDay;
    private String payWay;
    private String payStatus;
    private long userStartDate;
    private long userEndDate;
    private long payDate;
    private long chargeDate;
    private int approvalStatus;
    private String approvalMessage;
    private String taxBill;
    
    private CompanyInfoDTO company;
    private GradeEnvDTO gradeEnv;   
    
    
    public BillingDTO() {
        
    }
}
