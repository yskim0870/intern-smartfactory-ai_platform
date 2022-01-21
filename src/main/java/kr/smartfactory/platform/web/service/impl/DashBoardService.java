/**
 * 
 */
package kr.smartfactory.platform.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.service.IDashBoardService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.service.impl
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.01.18
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.18  Younghun Yu  최초 생성
 */
@Service
@Qualifier(DashBoardService.BEAN_QUALIFER)
public class DashBoardService implements IDashBoardService {
	
	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.service.impl.DashBoardService";

	/**
	 * @see kr.smartfactory.platform.web.service.IDashBoardService#selectCount(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Result<Map<String, Integer>> selectCount() {
		
		Map<String, Integer> countMap = new HashMap<>();
		
		countMap.put("manufacturer", 10);
		countMap.put("expert", 2);
		countMap.put("edgeGW", 19);
		countMap.put("bidNotice", 4);
		
		Result<Map<String, Integer>> res = new Result<Map<String,Integer>>(countMap);
		
		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IDashBoardService#selectEdgeCount()
	 */
	@Override
	public Result<Map<String, Integer>> selectEdgeCount() {
		
		Map<String, Integer> edgeCountMap = new HashMap<String, Integer>();
		
		edgeCountMap.put("totalCount", 19);
		edgeCountMap.put("normalCount", 15);
		edgeCountMap.put("nonNormalCount", 4);
		
		Result<Map<String, Integer>> res = new Result<Map<String,Integer>>(edgeCountMap);
		
		return res;
	}
}