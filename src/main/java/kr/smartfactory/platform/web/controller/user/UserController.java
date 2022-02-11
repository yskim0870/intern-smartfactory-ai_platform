/**
 * 
 */
package kr.smartfactory.platform.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import kr.smartfactory.platform.web.service.ICompanyService;
import kr.smartfactory.platform.web.service.impl.CompanyService;
import kr.smartfactory.platform.web.service.impl.UserService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.controller
 * @description : 사용자 처리에 대한 로직을 연결해주기 위한 컨트롤러
 * @author : Younghun Yu
 * @date : 2021.12.24
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.24  Younghun Yu  최초 생성
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	private ICompanyService manuService;
	
	@Autowired
	public UserController(@Qualifier(CompanyService.BEAN_QUALIFER) ICompanyService manuService) {
		this.manuService = manuService;
	}
	
	/**
	 * @methodName : createUser
	 * @description : 사용자 등록을 위한 컨트롤러
	 * @param user
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	@PutMapping(value = "")
	public ResponseEntity<Boolean> createUser(HttpServletRequest req, HttpServletResponse res, //
			@RequestBody UserDTO user){
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);		
	}
	
	/**
	 * @methodName : selectCompanyList
	 * @description : 제조사 관리, 전문업체 관리 페이지의 목록 조회 메소드
	 * @param name : 회사명
	 * @param condition : 업태
	 * @param industry : 업종
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.28
	 */
	@GetMapping(value = "/{userType}")
	public ResponseEntity<Result<PaginationDTO<UserDTO>>> selectCompanyList(
			HttpServletRequest req, HttpServletResponse res //
			, @PathVariable Integer userType //
			, @RequestParam(required = false) String name // 
			, @RequestParam(required = false) String condition //
			, @RequestParam(required = false) String industry //
			, @RequestParam(required = false) Integer status//
			, @RequestParam Integer pageNum//
			, @RequestParam Integer pageItemPerPage//
			, @RequestParam(required = false) String orderby//
			, @RequestParam(required = false) Boolean desc//
			) {
		return ResponseEntity.ok(manuService.selectCompanyList(userType, name, condition, industry, status));
	}
	
	/**
	 * @methodName : selectCompanyUser
	 * @description : 상세보기
	 * @param req
	 * @param res
	 * @param userType : 도메인, 제조사 구분짓기 위한 유저 타입
	 * @param id : 제조사 : 사업자 번호, 전문업체 : 전문업체명
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.02.09
	 */
	@GetMapping(value = "/{userType}/{id}")
	public ResponseEntity<Result<UserDTO>> selectCompanyUser(
			HttpServletRequest req, HttpServletResponse res //
			, @PathVariable Integer userType //
			, @PathVariable String id // 사업자 번호
			){
		return ResponseEntity.ok(manuService.selectCompanyUser(userType, id));
	}

	/**
	 * @methodName : updateUser
	 * @description : 
	 * @param user
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Boolean> updateUser(@RequestBody UserDTO user){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}