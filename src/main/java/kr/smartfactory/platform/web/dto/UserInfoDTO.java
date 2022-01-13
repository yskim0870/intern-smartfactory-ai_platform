package kr.smartfactory.platform.web.dto;

/**
 * 유저 정보 DTO
 * 
 * @since 2022. 1. 11. 오후 2:20:47
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 11. 오후 2:20:47 || Kyunghun Park || 최초 생성
 *
 */
public class UserInfoDTO {

    // 사용자 ID (Busniess Number)
    private String id;

    private String password;

    private int userType;

    private String businessNumber;

    // 사용자 이름
    private String name;

    // 연락처
    private String telNumber;

    // 이메일
    private String email;

    // 부서
    private String department;

    // 직급
    private String rank;

    // 가입 일자
    private String regDate;

    // 도메인 전문가 상태 정보
    private String status;

    /**
     * id을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
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
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * password을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * password 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * userType을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public int getUserType() {
        return userType;
    }

    /**
     * userType 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param userType
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * businessNumber을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getBusinessNumber() {
        return businessNumber;
    }

    /**
     * businessNumber 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param businessNumber
     */
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    /**
     * name을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * name 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * telNumber을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * telNumber 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param telNumber
     */
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    /**
     * email을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * email 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * department을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     * department 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * rank을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getRank() {
        return rank;
    }

    /**
     * rank 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * regDate을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * regDate 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param regDate
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    /**
     * status을(를) 호출합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * status 을(를) 지정합니다.
     *
     * @since 2022. 1. 12. 오후 2:22:44
     * @author "KyungHun Park"
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}