package kr.smartfactory.platform.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.smartfactory.platform.web.dao.IEdgeGatewayDao;
import kr.smartfactory.platform.web.dao.model.EdgeGateway;
import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.EdgeGWDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.query.Query;

/**
 * EdgeGateway DAO
 *
 * @since 2021. 12. 27. 오전 9:21:36
 * @author "KyungHun Park"
 *
 * @modified 2021. 12. 27. 오전 9:21:36 || Kyunghun Park || 최초 생성
 *
 */
@Repository(EdgeGatewayDao.BEAN_QUALIFIER)
public class EdgeGatewayDao implements IEdgeGatewayDao {

    private JdbcTemplate jdbcTemplate;

    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.dao.impl.EdgeGatewayDao";

    // JdbcTemplate 생성자 주입
    @Autowired
    public EdgeGatewayDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#createEdgeGW(kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public int createEdgeGW(EdgeGateway edgeGW) {
        try {
            // EdgeGateway ID, 기업 ID, 연동 시작 일자, 연동 종료 일자
            return jdbcTemplate.update(Query.EDGE_INSERT, edgeGW.getId(), edgeGW.getManagerId(), edgeGW.getStartDate(), edgeGW.getEndDate(), edgeGW.getUpdateDate(), edgeGW.getHost(), edgeGW.getPort(), edgeGW.getStatus());
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Target is empty");
            return 0;
        } catch (DuplicateKeyException duple) {
            System.out.println("이미 존재하는 ID입니다.");
            return -1;
        }
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#selectEdgeGW(java.lang.String,
     *      long, long, int, int, int, java.lang.String, boolean)
     */
    @Override
    public PaginationDTO<EdgeGWDTO> selectEdgeGW(String managerId, long startDate, long endDate, int itemCount, int pageNum, String order, boolean desc) {

        // 조회 결과 list와 총 데이터 건수를 담을 객체
        PaginationDTO<EdgeGWDTO> res = new PaginationDTO<>();

        // wild card에 입력될 값들의 list
        List<Object> params = new ArrayList<>();

        // 추가할 쿼리
        StringBuilder sb = new StringBuilder();

        // default 'SLELECT' Query
        String query = Query.EDGE_SELECT_ALL;

        // 데이터 건수 조회 Query
        String countQuery = Query.EDGE_COUNT;

        // Parameter의 값이 전달 되면 Query에 추가

        // 기업명
        if (managerId != null) {
            params.add("%" + managerId + "%");
            sb.append("AND manager_id LIKE ?");
        }

        // 검색할 연동 시작 일자 및 종료 일자
        if (startDate != 0 && endDate == 0) {
            params.add(startDate);
            sb.append("AND ? <= end_date");
        } else if (startDate == 0 && endDate != 0) {
            params.add(endDate);
            sb.append("AND ? >= start_date");
        } else if (startDate != 0 && endDate != 0) {
            params.add(startDate);
            params.add(endDate);
            sb.append("AND ? <= end_date AND ? >= start_date ");
        }

        // 정렬 기준
        if (order != null) {
            sb.append(" order by ");
            sb.append(order);
        } 
        // 내림차순 여부
        if (desc != false) {
            sb.append(" desc");
        } 

        // 총 데이터 건수를 위한 query
        countQuery = sb != null ? countQuery.replace("{where_clause}", sb.toString()) : countQuery;

        // 총 데이터 건수
        int allCount = jdbcTemplate.queryForObject(countQuery, Integer.class, params.toArray());

        // 한 페이지에 보일 데이터 건수
        params.add(itemCount);
        params.add((pageNum - 1) * itemCount);
        sb.append(" limit ? OFFSET ?");

        // parameter가 존재하는 경우 query 변경
        query = sb != null ? query.replace("{where_clause}", sb.toString()) : query;

        // 조회 결과를 list에 저장
        List<EdgeGWDTO> list = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(EdgeGWDTO.class), params.toArray());

        // PaginationDTO에 조회 결과 list 입력
        res.setItems(list);

        // PaginationDTO에 총 데이터 건수 입력
        res.setTotalCount(allCount);

        return res;
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#selectDetailEdgeGW(java.lang.String)
     */
    @Override
    public EdgeGWDTO selectDetailEdgeGW(String id) {

        // DTO의 DTO에 조회 결과 값을 입력
        try {
            return this.jdbcTemplate.queryForObject(Query.EDGE_SELECT_DETAIL, new RowMapper<EdgeGWDTO>() {
                public EdgeGWDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

                    EdgeGWDTO edge = new EdgeGWDTO();
                    
                    edge.setId(rs.getString("id"));
                    edge.setHost(rs.getString("host"));
                    edge.setPort(rs.getInt("port"));
                    edge.setStartDate(rs.getLong("start_date"));
                    edge.setEndDate(rs.getLong("end_date"));
                    edge.setUpdateDate(rs.getLong("update_date"));
                    edge.setStatus(rs.getInt("status"));

                    CompanyInfoDTO company = new CompanyInfoDTO();

                    company.setBusinessNumber(rs.getString("business_number"));
                    company.setName(rs.getString("name"));
                    company.setAddress(rs.getString("address"));
                    company.setTelNumber(rs.getString("tel_number"));
                    company.setCeoName(rs.getString("ceo_name"));

                    // CompanyDTO에 저장된 값을 edgeGWDTO에 저장
                    edge.setCompany(company);
                    return edge;
                }
                // query의 wild card에 입력될 값
            }, id);
        } catch (EmptyResultDataAccessException empty) {
            System.out.println("Target is empty");
            return null;
        }
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#updateEdgeGW(java.lang.String,
     *      kr.smartfactory.platform.web.dto.EdgeGWDTO)
     */
    @Override
    public int updateEdgeGW(EdgeGateway edgeGW) {

        // TODO 예외처리 질문
        // 업데이트할 Edge Gateway ID, 변경할 기업 ID, 연동 시작 일자, 연동 종료 일자
        return jdbcTemplate.update(Query.EDGE_UPDATE, edgeGW.getManagerId(), edgeGW.getStartDate(), edgeGW.getEndDate(), edgeGW.getUpdateDate(), edgeGW.getId());
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IEdgeGatewayDao#deleteEdgeGW(java.lang.String)
     */
    @Override
    public int deleteEdgeGW(String id) {

        // TODO 예외처리 질문
        // 삭제할 Edge Gateway ID
        return jdbcTemplate.update(Query.EDGE_DELETE, id);
    }
}
