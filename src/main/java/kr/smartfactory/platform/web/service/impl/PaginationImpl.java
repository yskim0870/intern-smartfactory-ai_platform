/**
 * 
 */
package kr.smartfactory.platform.web.service.impl;

import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.IPagination;

/**
 * @packageName : kr.smartfactory.platform.web.service.impl
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.26
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.26  Younghun Yu  최초 생성
 */
@Service
public class PaginationImpl<T> implements IPagination<T> {

	/**
	 * @see kr.smartfactory.platform.web.service.IPagination#pagination()
	 */
	@Override
	public PaginationDTO<T> pagination(T[] items) {
		
		PaginationDTO<T> pageObj = new PaginationDTO<T>();
		
		pageObj.setItems(items);
		pageObj.setTotalCount(pageObj.getItems().length);
		
		return pageObj;
	}
}