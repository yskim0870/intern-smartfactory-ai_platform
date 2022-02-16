package kr.smartfactory.platform.web.dto;

/**
 * 알람 전송 DTO
 * 
 * @since 2022. 2. 14. 오후 4:28:24
 * @author "KyungHun Park"
 *
 * @modified 2022. 2. 14. 오후 4:28:24 || Kyunghun Park || 최초 생성
 *
 */
public class AlarmSenderDTO {
    // 메시지 UUID
    private String id;

    // 수신자 ID
    private String receiveId;

    // 내용
    private String contents;

    // 예약 발송 여부 0: false, 1: true
    private int reserved;

    // 예약 발송 일자
    private long reserveDate;

    // 등록 일자
    private long registerDate;

    // 발송 상태 0: 미발송, 1: 발송
    private int sendStatus;

    /**
     * id을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
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
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * receiveId을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getReceiveId() {
        return receiveId;
    }

    /**
     * receiveId 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @param receiveId
     */
    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    /**
     * contents을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
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
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * reserved을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @return
     */
    public int getReserved() {
        return reserved;
    }

    /**
     * reserved 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @param reserved
     */
    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    /**
     * reserveDate을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @return
     */
    public long getReserveDate() {
        return reserveDate;
    }

    /**
     * reserveDate 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @param reserveDate
     */
    public void setReserveDate(long reserveDate) {
        this.reserveDate = reserveDate;
    }

    /**
     * registerDate을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @return
     */
    public long getRegisterDate() {
        return registerDate;
    }

    /**
     * registerDate 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @param registerDate
     */
    public void setRegisterDate(long registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * sendStatus을(를) 호출합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @return
     */
    public int getSendStatus() {
        return sendStatus;
    }

    /**
     * sendStatus 을(를) 지정합니다.
     *
     * @since 2022. 2. 14. 오후 4:28:20
     * @author "KyungHun Park"
     *
     * @param sendStatus
     */
    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public AlarmSenderDTO() {

    }
}
