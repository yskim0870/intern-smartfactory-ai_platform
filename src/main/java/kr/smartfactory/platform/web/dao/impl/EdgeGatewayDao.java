package kr.smartfactory.platform.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.smartfactory.platform.web.dao.IEdgeGatewayDao;
import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.query.Query;

@Repository(EdgeGatewayDao.BEAN_QUALIFIER)
public class EdgeGatewayDao implements IEdgeGatewayDao {

    private JdbcTemplate jdbcTemplate;

    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.dao.impl.EdgeGatewayDao";

    @Autowired
    public EdgeGatewayDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#createEdgeGW(kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public int createEdgeGW(EdgeGWDTO edgeGW) {

        return jdbcTemplate.update(Query.EDGE_INSERT, edgeGW.getId(), edgeGW.getManagerId(), edgeGW.getStartDate(), edgeGW.getEndDate());
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#selectEdgeGW(java.lang.String,
     *      long, long, int, int, int, java.lang.String, boolean)
     */
    @Override
    public PaginationDTO<EdgeGWDTO> selectEdgeGW(String managerId, long startDate, long endDate, int itemCount, int pageNum, int pageItemPerPage, String order, boolean desc) {
        List<Object> params = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        String query = Query.EDGE_SELECT_ALL;

        if (managerId != null) {
            params.add("%" + managerId + "%");
            sb.append("AND manager_id LIKE ?");
        }
        if (startDate != 0) {
            params.add("%" + startDate + "%");
            sb.append("AND start_date LIKE ?");
        }
        if (endDate != 0) {
            params.add("%" + endDate + "%");
            sb.append("AND end_date LIKE ?");
        }
        if (itemCount != 0) {
            params.add("%" + itemCount + "%");
            sb.append("AND item_count LIKE ?");
        }
        if (pageNum != 0) {
            params.add("%" + pageNum + "%");
            sb.append("AND page_num LIKE ?");
        }
        if (pageItemPerPage != 0) {
            params.add("%" + pageItemPerPage + "%");
            sb.append("AND page_item_per_page LIKE ?");
        }

        if (order != null) {
            params.add(order);
            sb.append("order by ?");
        }

        if (desc != false) {
            params.add(desc);
            sb.append(""
                    + "?");
        }

        if (itemCount != 0) {
            params.add(itemCount);
            sb.append("limit ?");
        }

        query = sb != null ? query.replace("{where_clause}", sb.toString()) : query;

        List<EdgeGWDTO> list = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(EdgeGWDTO.class), params.toArray());
        
        int allCount = list.size(); // 한 페이지에 개수가 나오는거네

        PaginationDTO<EdgeGWDTO> res = new PaginationDTO<>();
        res.setItems(list);
        res.setTotalCount(allCount);

        return res;
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#selectDetailEdgeGW(java.lang.String)
     */
    @Override
    public EdgeGWDTO selectDetailEdgeGW(String id) {

        return this.jdbcTemplate.queryForObject(Query.EDGE_SELECT_DETAIL, new RowMapper<EdgeGWDTO>() {
            public EdgeGWDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                EdgeGWDTO edge = new EdgeGWDTO();
                edge.setHost(rs.getString("host"));
                edge.setPort(rs.getInt("port"));
                edge.setUpdateDate(rs.getLong("update_date"));
                edge.setStatus(rs.getInt("status"));

                CompanyInfoDTO company = new CompanyInfoDTO();

                company.setBusinessNumber(rs.getString("business_number"));
                company.setName(rs.getString("name"));
                company.setAddress(rs.getString("address"));
                company.setTelNumber(rs.getString("tel_number"));
                company.setCeoName(rs.getString("ceo_name"));

                edge.setCompany(company);
                return edge;
            }
        }, id, id);
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#updateEdgeGW(java.lang.String,
     *      kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public int updateEdgeGW(String id, EdgeGWDTO edgeGW) {

        return jdbcTemplate.update(Query.EDGE_UPDATE, edgeGW.getManagerId(), edgeGW.getStartDate(), edgeGW.getEndDate(), id);
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#deleteEdgeGW(java.lang.String)
     */
    @Override
    public int deleteEdgeGW(String id) {

        return jdbcTemplate.update(Query.EDGE_DELETE, id);
    }
}
