/**
 * 
 */
package kr.smartfactory.platform.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import kr.smartfactory.platform.web.dto.common.UserGradeDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import kr.smartfactory.platform.web.service.impl.UserService;

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
	public ResponseEntity<Boolean> createUser(@RequestBody UserDTO user){
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);		
	}
	
	/**
	 * @methodName : selectUserGrade
	 * @description : 사용자 권한 별 목록 조회
	 * @param grade
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	@GetMapping(value = "/{grade}")
	public ResponseEntity<PaginationDTO<CompanyInfoDTO>> selectUserGrade(@PathVariable UserGradeDTO grade){
		return new ResponseEntity<>(userService.selectUserGrade(grade), HttpStatus.OK);
	}

	/**
	 * @methodName : selectUserDetail
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> selectUserDetail(@PathVariable UserInfoDTO user){
		return new ResponseEntity<>(userService.selectUserDetail(user), HttpStatus.OK);
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