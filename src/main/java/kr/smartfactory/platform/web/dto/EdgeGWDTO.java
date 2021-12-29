package kr.smartfactory.platform.web.dto;

public class EdgeGWDTO {
    private String id;
    private String managerId;
    private String host;
    private long startDate;
    private long endDate;
    private long updateDate;
    private int port;
    private int status;

    private CompanyInfoDTO company;

    // 기본 생성자
    public EdgeGWDTO() {

    }
    

    
    /**
     * id을(를) 호출합니다.
     *
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
     * @author "KyungHun Park"
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * company을(를) 호출합니다.
     *
     * @since 2021. 12. 26. 오후 2:59:33
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
     * @since 2021. 12. 26. 오후 2:59:33
     * @author "KyungHun Park"
     *
     * @param company
     */
    public void setCompany(CompanyInfoDTO company) {
        this.company = company;
    }

    /** @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EdgeGWDTO [id=");
        builder.append(id);
        builder.append(", managerId=");
        builder.append(managerId);
        builder.append(", host=");
        builder.append(host);
        builder.append(", startDate=");
        builder.append(startDate);
        builder.append(", endDate=");
        builder.append(endDate);
        builder.append(", updateDate=");
        builder.append(updateDate);
        builder.append(", port=");
        builder.append(port);
        builder.append(", status=");
        builder.append(status);
        builder.append(", company=");
        builder.append(company);
        builder.append("]");
        return builder.toString();
    }
}
