/**
 * 
 */
package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
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
public interface IManuService {
	
	/**
	 * @methodName : selectManuList
	 * @description : 
	 * @param name : 업체명
	 * @param condition : 업태
	 * @param industry : 종목
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.26
	 */
	public Result<PaginationDTO<CompanyInfoDTO>> selectManuList(String name, String condition, String industry);
	
}