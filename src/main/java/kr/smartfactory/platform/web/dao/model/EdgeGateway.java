package kr.smartfactory.platform.web.dao.model;

/**
 * Edge Gateway 1:1 테이블 모델 
 * 
 * @since 2021. 12. 30. 오후 2:41:08
 * @author "KyungHun Park"
 *
 * @modified 2021. 12. 30. 오후 2:41:08 || Kyunghun Park || 최초 생성
 *
 */
public class EdgeGateway {

    // EdgeGateway 고유 ID
    private String id;

    // 기업 ID
    private String managerId;

    // IP
    private String host;

    // 연동 시작 일자
    private long startDate;

    // 연동 종료 일자
    private long endDate;

    // 최종 연동 일자
    private long updateDate;

    // 포트 번호
    private int port;

    // 작동 여부
    private int status;
    
    //기본 생성자
    public EdgeGateway(){
        
    }
    
    public EdgeGateway(String id, String managerId, long startDate, long endDate, long updateDate, String host, int port, int status) {
        this.id = id;
        this.managerId = managerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
        this.host = host;
        this.port = port;
        this.status = status;
    }
    
    public EdgeGateway(String id, String managerId, long startDate, long endDate, long updateDate) {
        this.id = id;
        this.managerId = managerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateDate = updateDate;
    }
   

    
    /**
     * id을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
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
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * managerId을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * managerId 을(를) 지정합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param managerId
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    /**
     * host을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     * host 을(를) 지정합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * startDate을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @return
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * startDate 을(를) 지정합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param startDate
     */
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    /**
     * endDate을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
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
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param endDate
     */
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    /**
     * updateDate을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @return
     */
    public long getUpdateDate() {
        return updateDate;
    }

    /**
     * updateDate 을(를) 지정합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param updateDate
     */
    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * port을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * port 을(를) 지정합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * status을(를) 호출합니다.
     *
     * @since 2021. 12. 30. 오후 1:45:53
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
     * @since 2021. 12. 30. 오후 1:45:53
     * @author "KyungHun Park"
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
