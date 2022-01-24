/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:56:05
*/

/**
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:56:05
*/
package kr.smartfactory.platform.web.dao.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.smartfactory.platform.web.dao.IUserDao;
import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.UserDTO;
import kr.smartfactory.platform.web.dto.UserInfoDTO;
import kr.smartfactory.platform.web.query.Query;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 12. 8. 오전 9:56:05
 *
 */
@Repository(UserDao.BEAN_QUALIFIER)
public class UserDao extends DBGenericDao implements IUserDao {

    private JdbcTemplate jdbcTemplate;

    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.dao.impl.UserDao";

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @see kr.smartfactory.platform.web.dao.IUserDao#detailUser(java.lang.String)
     */
    @Override
    public UserDTO detailUser(String id) {

        CompanyInfoDTO company = new CompanyInfoDTO();
        UserInfoDTO userInfo = new UserInfoDTO();

        // 사업자 번호인지 확인
        String pattern = "^\\d{3}-\\d{2}-\\d{5}$";

        // 사업자 번호인 경우
        if (Pattern.matches(pattern, id) == true) {

            company = jdbcTemplate.queryForObject(Query.COMPANY_SELECT_DETAIL_BUSNIESS, BeanPropertyRowMapper.newInstance(CompanyInfoDTO.class), id);

            userInfo = jdbcTemplate.queryForObject(Query.USER_SELECT_DETAIL_BUSNIESS, BeanPropertyRowMapper.newInstance(UserInfoDTO.class), id);

            return new UserDTO(company, userInfo);
        }
        // ID인 경우
        else {
            userInfo = jdbcTemplate.queryForObject(Query.USER_SELECT_DETAIL_ID, BeanPropertyRowMapper.newInstance(UserInfoDTO.class), id);
            
            company = jdbcTemplate.queryForObject(Query.COMPANY_SELECT_DETAIL_BUSNIESS, BeanPropertyRowMapper.newInstance(CompanyInfoDTO.class), userInfo.getBusinessNumber());

            return new UserDTO(company, userInfo);
        }
    }

}
