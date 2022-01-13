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
import org.springframework.stereotype.Repository;
import kr.smartfactory.platform.web.dao.IUserDao;
import kr.smartfactory.platform.web.dao.entity.Company;
import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;

/**
 * @packageName : kr.smartfactory.platform.web.dao.impl
 * @description : DB에 접근하여 사용자에 대한 로직을 처리하기 위한 DAO
 * @author : Younghun Yu
 * @date : 2021.12.24
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.24  Younghun Yu  최초 생성
 */
@Repository(UserDaoImpl.BEAN_QUALIFIER)
public class UserDaoImpl extends DBGenericDao implements IUserDao {

	public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.dao.impl.UserDaoImpl";

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#createUser(kr.smartfactory.platform.web.dto.common.UserDTO)
	 */
	@Override
	public Integer createUser(UserDTO user) {
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
	 * @see kr.smartfactory.platform.web.dao.IUserDao#selectUserDetail(kr.smartfactory.platform.web.dao.entity.User)
	 */
	@Override
	public UserDTO selectUserDetail(String id) {
		
//		User userInfo = jdbcTemplate.queryForObject(Query.SELECT_USER_QUERY, (rs, rowNum) -> new User(rs), id);
//		Company companyInfo = jdbcTemplate.queryForObject(Query., null, null)
		// TODO 상세보기: 사업자번호로 업체정보 조회
//		UserDTO userDetail = new UserDTO(userInfo, companyInfo);
		
		return null;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#updateUser(kr.smartfactory.platform.web.dao.entity.User)
	 */
	@Override
	public Integer updateUser(User user) {
		
		
		
		return null;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IUserDao#selectCompany(java.lang.String)
	 */
	@Override
	public CompanyInfoDTO selectCompany(String businessNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
