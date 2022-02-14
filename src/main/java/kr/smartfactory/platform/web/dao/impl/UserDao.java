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

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import open.commons.Result;
import open.commons.function.SQLConsumer;

import kr.smartfactory.platform.web.dao.IUserDao;
import kr.smartfactory.platform.web.dao.entity.Company;
import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
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

	public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.dao.impl.UserDao";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#createUser(kr.smartfactory.platform.web.dto.common.UserDTO)
	 */
	@Override
	public Integer createUser(UserDTO user) {
		return null;
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

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#selectCompany(java.lang.String)
	 */
	@Override
	public CompanyInfoDTO selectCompany(String businessNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#selectUser(kr.smartfactory.platform.web.dao.entity.Company)
	 */
	@Override
	public List<Company> selectUser(Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see kr.smartfactory.platform.web.dao.IUserDao#selectUserByIdAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Result<User> selectUserByIdAndPassword(String id, String password) {
		String sql = getQuery("userDao.select.user.by.id.pw");
		logger.debug("query: {}", sql);
		logger.debug("parameter: {}, {}", id, password);
		return getObject(sql, SQLConsumer.setParameters(id, password), User.class);
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#selectUserDetail(kr.smartfactory.platform.web.dao.entity.User)
	 */
	@Override
	public UserDTO selectUserDetail(String id) {

		// User userInfo = jdbcTemplate.queryForObject(Query.SELECT_USER_QUERY, (rs, rowNum) -> new User(rs), id);
		// Company companyInfo = jdbcTemplate.queryForObject(Query., null, null)
		// TODO 상세보기: 사업자번호로 업체정보 조회
		// UserDTO userDetail = new UserDTO(userInfo, companyInfo);

		return null;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#updateUser(kr.smartfactory.platform.web.dao.entity.User)
	 */
	@Override
	public Integer updateUser(User user) {

		return null;
	}
}
