/**
 * 
 */
package kr.smartfactory.platform.web.service;

import open.commons.Result;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import kr.smartfactory.platform.web.dto.common.UserGradeDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;

/**
 * @packageName : kr.smartfactory.platform.web.service
 * @description : 사용자(업체)에 대한 로직을 처리하기 위한 인터페이스
 * @author : Younghun Yu
 * @date : 2021.12.24
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.24  Younghun Yu  최초 생성
 */
public interface IUserService {

    /**
     * Business number or UserID를 이용한 사용자 및 회사 정보 조회
     * 
     * @return : 성공/실패 여부, 실패 시 메시지, 조회 결과
     *
     * @since 2022. 1. 11. 오후 2:33:52
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 11. 오후 2:33:52 || Kyunghun Park || 최초 생성
     *
     */
    public Result<UserDTO> detailUser(String id);

    /**
	 * @methodName : createUser
	 * @description : 
	 * @param user
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public Boolean createUser(UserDTO user);
	
	/**
	 * @methodName : selectUserGrade
	 * @description : 
	 * @param userGrade.grade
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public PaginationDTO<CompanyInfoDTO> selectUserGrade(UserGradeDTO userGrade);
	
	/**
	 * @methodName : selectUserDetail
	 * @description : 
	 * @param userInfo.id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public UserDTO selectUserDetail(UserInfoDTO userInfo);
	
	/**
	 * @methodName : updateUser
	 * @description : 
	 * @param user
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public Boolean updateUser(UserDTO user);
	
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
