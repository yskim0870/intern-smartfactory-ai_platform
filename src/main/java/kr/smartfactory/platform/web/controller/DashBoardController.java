/**
 * 
 */
package kr.smartfactory.platform.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.smartfactory.platform.web.service.IDashBoardService;
import kr.smartfactory.platform.web.service.impl.DashBoardService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.controller
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.01.17
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.17  Younghun Yu  최초 생성
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashBoardController {
	
	private IDashBoardService dashboardService;
	
	@Autowired
	public DashBoardController(@Qualifier(DashBoardService.BEAN_QUALIFER) IDashBoardService dashBoardService) {
		this.dashboardService = dashBoardService;
	}

	/**
	 * @methodName : selectCount
	 * @description : 대시보드 상단의 카운트 조회
	 * @param id : 제조사, 입찰공고, EdgeGW, IT전문업체를 각각 구분하여 조회하기 위한 String
	 * 			  // - bidNotice, edgeGW, company(제조사, 계약업체는 company_info 테이블에서 type으로 구분하여 조회)
	 * @param type : 제조사와 계약업체 구분하기 위한 타입
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.17
	 */
	@GetMapping(value = "")
	public ResponseEntity<Result<Map<String, Integer>>> selectCount(){
		return ResponseEntity.ok(dashboardService.selectCount());
	}
	
	@GetMapping(value = "/edge-gw")
	public ResponseEntity<Result<Map<String, Integer>>> selectEdgeCount(){
		return ResponseEntity.ok(dashboardService.selectEdgeCount());
	}
}