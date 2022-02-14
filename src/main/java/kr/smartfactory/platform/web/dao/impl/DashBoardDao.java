/**
 * 
 */
package kr.smartfactory.platform.web.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;

import kr.smartfactory.platform.web.dao.IDashBoardDao;

/**
 * @packageName : kr.smartfactory.platform.web.dao.impl
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.01.18
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.18  Younghun Yu  최초 생성
 */
@Qualifier(DashBoardDao.BEAN_QUALIFER)
public class DashBoardDao implements IDashBoardDao {

	public static final String BEAN_QUALIFER = "kr.smartfactory.platform.web.dao.impl.DashBoardDao";

	/**
	 * @see kr.smartfactory.platform.web.dao.IDashBoardDao#selectCount(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer selectCount(String id, Integer type) {
		
		
		
		return null;
	}

}
