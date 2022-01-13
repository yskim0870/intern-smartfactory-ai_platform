/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:32
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
 * @since: 2021. 12. 8. 오전 9:55:32
*/
package kr.smartfactory.platform.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dao.IUserDao;
import kr.smartfactory.platform.web.dao.impl.UserDao;
import kr.smartfactory.platform.web.dto.UserDTO;
import kr.smartfactory.platform.web.service.IUserService;
import open.commons.Result;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 12. 8. 오전 9:55:32
 *
 */
@Service(UserService.BEAN_QUALIFIER)
public class UserService implements IUserService {

    private IUserDao userDao;
    public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.service.impl.UserService";

    @Autowired
	public UserService(@Qualifier(UserDao.BEAN_QUALIFIER) IUserDao userDao) {
	    this.userDao = userDao;
	}

    /**
     * @see kr.smartfactory.platform.web.service.IUserService#detailUser(java.lang.String)
     */
    @Override
    public Result<UserDTO> detailUser(String businessNumber) {

        UserDTO daoRes = new UserDTO();
        
        daoRes = userDao.detailUser(businessNumber);
        
        
        return new Result<>(daoRes);
    }

}
