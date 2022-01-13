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
    private CompanyInfoDTO companyInfoDTO;
    private UserInfoDTO userInfoDTO;
    private UserGradeDTO userGradeDTo;

    public UserDTO() {

    }

    public UserDTO(CompanyInfoDTO company, UserInfoDTO userInfo) {
        this.companyInfoDTO = company;
        this.userInfoDTO = userInfo;
    }

    /**
     * companyInfoDTO을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @return
     */
    public CompanyInfoDTO getCompanyInfoDTO() {
        return companyInfoDTO;
    }

    /**
     * companyInfoDTO 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @param companyInfoDTO
     */
    public void setCompanyInfoDTO(CompanyInfoDTO companyInfoDTO) {
        this.companyInfoDTO = companyInfoDTO;
    }

    /**
     * userInfoDTO을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @return
     */
    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    /**
     * userInfoDTO 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @param userInfoDTO
     */
    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    /**
     * userGradeDTo을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @return
     */
    public UserGradeDTO getUserGradeDTo() {
        return userGradeDTo;
    }

    /**
     * userGradeDTo 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:46
     * @author "KyungHun Park"
     *
     * @param userGradeDTo
     */
    public void setUserGradeDTo(UserGradeDTO userGradeDTo) {
        this.userGradeDTo = userGradeDTo;
    }
}
