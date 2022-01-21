/**
 * 
 */
package kr.smartfactory.platform.web.dao;

/**
 * @packageName : kr.smartfactory.platform.web.dao
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.01.18
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.18  Younghun Yu  최초 생성
 */
public interface IDashBoardDao {

	/**
	 * @methodName : selectCount
	 * @description : 
	 * @param id
	 * @param type
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.18
	 */
	public Integer selectCount(String id, Integer type);
	
}
