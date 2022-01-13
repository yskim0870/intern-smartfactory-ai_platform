package kr.smartfactory.platform.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.smartfactory.platform.web.dto.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.service.impl.CompanyService;
import open.commons.Result;

/**
 * 
 * @since 2022. 1. 12. 오전 10:00:25
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 12. 오전 10:00:25 || Kyunghun Park || 최초 생성
 *
 */
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 
     * @param request
     * @param response
     * @param name
     * @param industryType
     * @param condition
     * @param status
     * @param pageNum
     * @param pageItemPerPage
     * @param order
     * @param desc
     * @return :
     *
     * @since 2022. 1. 12. 오전 10:00:27
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 12. 오전 10:00:27 || Kyunghun Park || 최초 생성
     *
     */
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public ResponseEntity<Result<PaginationDTO<CompanyInfoDTO>>> detailCompany(//
            HttpServletRequest request//
            , HttpServletResponse response//
            , @RequestParam(value = "", required = false) @Size(max = 36) String name//
            , @RequestParam(value = "", required = false) @Size(max = 36) String industryType//
            , @RequestParam(value = "", required = false) @Size(max = 36) String condition//
            , @RequestParam(value = "", defaultValue = "0", required = false) int status//
            , @RequestParam(value = "", defaultValue = "0", required = false) int pageNum//
            , @RequestParam(value = "", defaultValue = "0", required = false) int pageItemPerPage//
            , @RequestParam(value = "", required = false) @Size(max = 36) String order//
            , @RequestParam(value = "", required = false) boolean desc//
    ) {
        return new ResponseEntity<>(companyService.selectCompany(name, industryType, condition, status, pageNum, pageItemPerPage, order, desc), HttpStatus.OK);
    }
    
    
}
