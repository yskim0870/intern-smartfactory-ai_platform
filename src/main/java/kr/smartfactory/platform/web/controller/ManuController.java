/**
 * 
 */
package kr.smartfactory.platform.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.controller
 * @description :
 * @author : Younghun Yu
 * @date : 2022.01.24
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2022.01.24 Younghun Yu 최초 생성
 */
//@Controller(ManuController.BEAN_QUALIFER)
//@RequestMapping(value = "/manufacturer")
//public class ManuController {
//
//	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.controller/ManuController";
//
//	@GetMapping(value = "")
//	public ResponseEntity<Result<PaginationDTO<CompanyInfoDTO>>> selectManuList() {
//		return null;
//	}
//
//}
