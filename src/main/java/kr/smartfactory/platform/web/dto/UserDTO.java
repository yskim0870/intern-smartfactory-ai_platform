package kr.smartfactory.platform.web.dto;

/**
 * 회사 정보, 사용자 정보, 사용자 구분
 * 
 * @since 2022. 1. 11. 오후 2:42:13
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 11. 오후 2:42:13 || Kyunghun Park || 최초 생성
 *
 */
public class UserDTO {
    private CompanyInfoDTO companyInfo;
    private UserInfoDTO userInfo;
    private UserGradeDTO userGrade;

    public UserDTO() {

    }

    public UserDTO(CompanyInfoDTO company, UserInfoDTO userInfo) {
        this.companyInfo = company;
        this.userInfo = userInfo;
    }

    /**
     * companyInfoDTO을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @return
     */
    public CompanyInfoDTO getCompanyInfo() {
        return companyInfo;
    }

    /**
     * companyInfoDTO 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @param companyInfoDTO
     */
    public void setCompanyInfo(CompanyInfoDTO companyInfoDTO) {
        this.companyInfo = companyInfoDTO;
    }

    /**
     * userInfoDTO을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @return
     */
    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    /**
     * userInfoDTO 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @param userInfoDTO
     */
    public void setUserInfo(UserInfoDTO userInfoDTO) {
        this.userInfo = userInfoDTO;
    }

    /**
     * userGradeDTo을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @return
     */
    public UserGradeDTO getUserGrade() {
        return userGrade;
    }

    /**
     * userGradeDTo 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @param userGradeDTo
     */
    public void setUserGrade(UserGradeDTO userGradeDTo) {
        this.userGrade = userGradeDTo;
    }
}
