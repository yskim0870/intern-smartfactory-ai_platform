package kr.smartfactory.platform.web.dto;

import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

public class AlarmHistoryDTO {
    // 메시지 UUID
    private String id;

    // 수신자 ID
    private String receiveId;

    // 내용
    private String contents;

    // 수신 날짜
    private long receiveDate;

    // 확인 여부 0: 미확인, 1: 확인
    private int checked;

    // 기업 정보
    private CompanyInfoDTO company;

    /**
     * id을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
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
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * receivedId을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getReceiveId() {
        return receiveId;
    }

    /**
     * receivedId 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @param receivedId
     */
    public void setReceiveId(String receivedId) {
        this.receiveId = receivedId;
    }

    /**
     * contents을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getContents() {
        return contents;
    }

    /**
     * contents 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * receiveDate을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @return
     */
    public long getReceiveDate() {
        return receiveDate;
    }

    /**
     * receiveDate 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @param receiveDate
     */
    public void setReceiveDate(long receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * checked을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @return
     */
    public int getChecked() {
        return checked;
    }

    /**
     * checked 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @param checked
     */
    public void setChecked(int checked) {
        this.checked = checked;
    }

    /**
     * company을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오전 11:49:45
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
     * @since 2022. 2. 14. 오전 11:49:45
     * @author "KyungHun Park"
     *
     * @param company
     */
    public void setCompany(CompanyInfoDTO company) {
        this.company = company;
    }

    public AlarmHistoryDTO() {

    }
}
