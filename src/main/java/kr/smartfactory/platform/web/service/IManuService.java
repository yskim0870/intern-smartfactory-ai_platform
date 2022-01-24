/**
 * 
 */
package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

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
public interface IManuService {
	
	/**
	 * @methodName : selectManuList
	 * @description : 
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.24
	 */
	public PaginationDTO<CompanyInfoDTO> selectManuList();
	
}