/**
 * 
 */
package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.service
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.01.24
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.24  Younghun Yu  최초 생성
 */
public interface ICompanyService {
	
	/**
	 * @methodName : selectManuList
	 * @description : 
	 * @param userType : 제조사, 전문업체 관리 페이지를 위한 userType
	 * @param name : 업체명
	 * @param condition : 업태
	 * @param industry : 종목
	 * @param status : 승인상태
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.26
	 */
	public Result<PaginationDTO<UserDTO>> selectCompanyList(Integer userType, String name, String condition, String industry, Integer status);
	
	/**
	 * @methodName : selectCompanyUser
	 * @description : 업체 상세보기
	 * @param userType : 제조사, 도메인 전문 업체를 구분하기 위한 번호
	 * @param id : 사업자번호, 전문업체명
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.02.04
	 */
	public Result<UserDTO> selectCompanyUser(Integer userType, String id);
}