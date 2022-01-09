package kr.smartfactory.platform.web.query;

/**
 * DAO에서 사용할 Query
 *
 * @since 2021. 12. 28. 오후 5:57:15
 * @author "KyungHun Park"
 *
 * @modified 2021. 12. 28. 오후 5:57:15 || Kyunghun Park || 최초 생성
 *
 */
public class Query {
    
    // Edge Gateway Dao Query
    public static final String EDGE_INSERT = "INSERT INTO edge_gw_info(id, manager_id, start_date, end_date, update_date, host, port, status) VALUES(?,?,?,?,?,?,?,?)";
    
    public static final String EDGE_SELECT_ALL = "SELECT * FROM edge_gw_info WHERE 1=1 {where_clause}";
    
    public static final String EDGE_SELECT_DETAIL = "SELECT edge_gw_info.id, edge_gw_info.start_date, edge_gw_info.end_date, edge_gw_info.update_date, edge_gw_info.status, edge_gw_info.host, edge_gw_info.port, company_info.business_number, company_info.name, company_info.address, company_info.tel_number, company_info.ceo_name FROM edge_gw_info, company_info  WHERE company_info.name = edge_gw_info.manager_id AND edge_gw_info.id = ?";
    
    public static final String EDGE_UPDATE = "UPDATE edge_gw_info SET manager_id = ?, start_date = ?, end_date = ? WHERE id = ?";
    
    public static final String EDGE_DELETE = "DELETE FROM edge_gw_info WHERE id = ?";
    
    public static final String EDGE_COUNT = "SELECT COUNT(*) FROM edge_gw_info WHERE 1=1 {where_clause}";
}
