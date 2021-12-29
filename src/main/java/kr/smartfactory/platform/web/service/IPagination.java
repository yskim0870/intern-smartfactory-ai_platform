/**
 * 
 */
package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.PaginationDTO;

/**
 * @packageName : kr.smartfactory.platform.web.service
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.26
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.26  Younghun Yu  최초 생성
 */
public interface IPagination<T> {

	public PaginationDTO<T> pagination(T[] items);
	
}