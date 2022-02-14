/**
 * 
 */
package kr.smartfactory.platform.web.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

/**
 * @packageName : kr.smartfactory.platform.web.controller
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.24
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.24  Younghun Yu  최초 생성
 */
@Controller
@RequestMapping(value = "/companies")
public class CompanyController {
	
	/**
	 * @methodName : selectCompany
	 * @description : 사업자 번호로 업체정보를 조회하기 위한 컨트롤러
	 * @param businessNumber
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	@GetMapping(value = "/{businessNumber}")
	public ResponseEntity<CompanyInfoDTO> selectCompany(@PathVariable String businessNumber){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
