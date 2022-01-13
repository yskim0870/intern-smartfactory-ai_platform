package kr.smartfactory.platform.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.smartfactory.platform.web.dto.UserDTO;
import kr.smartfactory.platform.web.service.impl.UserService;
import open.commons.Result;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 기업 정보 사용자 정보 조회
     * 
     * @param request
     * @param response
     * @param businessNumber
     * @return :
     *
     * @since 2022. 1. 11. 오후 3:51:03
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 11. 오후 3:51:03 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/users/{business_number}", method = RequestMethod.GET)
    public ResponseEntity<Result<UserDTO>> detailUser(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @PathVariable(value = "business_number", required = false) @Size(max = 36) String businessNumber//
    ) {
        return new ResponseEntity<>(userService.detailUser(businessNumber), HttpStatus.OK);
    }
    
}
