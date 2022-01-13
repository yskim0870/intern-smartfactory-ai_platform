/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:39
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
 * @since: 2021. 12. 8. 오전 9:55:39
*/
package kr.smartfactory.platform.web.dao;

import java.util.List;

import kr.smartfactory.platform.web.dao.entity.Company;
import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 12. 8. 오전 9:55:39
 *
 */
public interface IUserDao {
	
	/**
	 * @methodName : createUser
	 * @description : 
	 * @param user
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.26
	 */
	public Integer createUser(UserDTO user);

	/**
	 * @methodName : selectUser
	 * @description : 
	 * @param company
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public List<Company> selectUser(Company company);
	
	/**
	 * @methodName : selectUserDetail
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public UserDTO selectUserDetail(String id);
	
	/**
	 * @methodName : updateUser
	 * @description : 
	 * @param user
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public Integer updateUser(User user);
	
	/**
	 * @methodName : selectCompany
	 * @description : 
	 * @param businessNumber
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public CompanyInfoDTO selectCompany(String businessNumber);
}
