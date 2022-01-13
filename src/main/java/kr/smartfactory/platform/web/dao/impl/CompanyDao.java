package kr.smartfactory.platform.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.smartfactory.platform.web.dao.ICompanyDao;
import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;

/**
 * 
 * @since 2022. 1. 12. 오전 10:07:16
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 12. 오전 10:07:16 || Kyunghun Park || 최초 생성
 *
 */
@Repository(CompanyDao.BEAN_QUALIFIER)
public class CompanyDao implements ICompanyDao {

    private JdbcTemplate jdbcTemplate;

    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.dao.impl.CompanyDao";

    // JdbcTemplate 생성자 주입
    @Autowired
    public CompanyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @see kr.smartfactory.platform.web.dao.ICompanyDao#selectCompany(java.lang.String,
     *      java.lang.String, java.lang.String, int, int, int, java.lang.String,
     *      boolean)
     */
    @Override
    public PaginationDTO<CompanyInfoDTO> selectCompany(String name, String industryType, String condition, int status, int pageNum, int pageItemPerPage, String order, boolean desc) {

        PaginationDTO<CompanyInfoDTO> res = new PaginationDTO<>();

        // List<Object> params = new ArrayList<>();

        List<CompanyInfoDTO> list = jdbcTemplate.query("select * from company_info", BeanPropertyRowMapper.newInstance(CompanyInfoDTO.class));

        res.setItems(list);

        // res.getTotalCount(); TODO : Pagination은 후에 추가

        return res;
    }

}
