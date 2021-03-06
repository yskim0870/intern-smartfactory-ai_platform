/**
 * 
 */
package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.dashboard.DashboardCountDTO;
import kr.smartfactory.platform.web.dto.dashboard.EdgeGWCountDTO;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.service
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.01.18
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.18  Younghun Yu  최초 생성
 */
public interface IDashBoardService {

	/**
	 * @methodName : selectCount
	 * @description : 
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.18
	 */
	public Result<DashboardCountDTO> selectCount();
	
	/**
	 * @methodName : selectEdgeCount
	 * @description : 
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.18
	 */
	public Result<EdgeGWCountDTO> selectEdgeCount();
}
