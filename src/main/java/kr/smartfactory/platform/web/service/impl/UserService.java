/**
 * 
 */
package kr.smartfactory.platform.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import open.commons.Result;

import kr.smartfactory.platform.web.dao.IUserDao;
import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dao.impl.UserDao;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import kr.smartfactory.platform.web.dto.common.UserGradeDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import kr.smartfactory.platform.web.service.IUserService;

/**
 * @packageName : kr.smartfactory.platform.web.service.impl
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.24
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.24  Younghun Yu  최초 생성
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
    public Result<UserDTO> detailUser(String id) {

        UserDTO daoRes = new UserDTO();
        
        daoRes = userDao.detailUser(id);
        
        
        return new Result<>(daoRes);
    }
	
	/**
	 * @see kr.smartfactory.platform.web.service.IUserService#createUser(kr.smartfactory.platform.web.dto.common.UserDTO)
	 */
	@Override
	public Boolean createUser(UserDTO user) {
		
		int createUserResult = userDao.createUser(user);
		
		if(createUserResult > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
//	- 회사 ID
//	- 회사명
//	- 주소
//	- 주요사업
//	- 대표자
//	- 대표번호
//	- 승인상태 (IT 전문업체)

	/**
	 * @see kr.smartfactory.platform.web.service.IUserService#selectUserGrade(kr.smartfactory.platform.web.dto.common.UserGradeDTO)
	 */
	@Override
	public PaginationDTO<CompanyInfoDTO> selectUserGrade(UserGradeDTO userGrade) {
		// 권한에 대한 테이블 정보 필요?
		// 회사 전체 조회
		// Object[] page = userDao.selectCompany(사업자 번호);
		
		// PaginationDTO<CompanyInfoDTO> pageObj =  pageService.pagination(page);
		
		// return pageObj;
		
		return null;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IUserService#selectUserDetail(kr.smartfactory.platform.web.dto.common.UserInfoDTO)
	 */
	@Override
	public UserDTO selectUserDetail(UserInfoDTO user) {
		
		// TODO 검증과정 필요
		UserDTO userDetail = userDao.selectUserDetail(user.getId());
		
		return userDetail;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IUserService#updateUser(kr.smartfactory.platform.web.dto.common.UserDTO)
	 */
	@Override
	public Boolean updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IUserService#selectCompany(java.lang.String)
	 */
	@Override
	public CompanyInfoDTO selectCompany(String businessNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * (non-Javadoc)
	 * @see kr.smartfactory.platform.web.service.IUserService#getUserByIdAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Result<User> getUserByIdAndPassword(String id, String password) {
		return userDao.selectUserByIdAndPassword(id, password);
	}
}
