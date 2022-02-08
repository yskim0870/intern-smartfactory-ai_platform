/**
 * 
 */
package kr.smartfactory.platform.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import kr.smartfactory.platform.web.service.IManuService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.service.impl
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.01.26
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.26  Younghun Yu  최초 생성
 */
@Service(ManuService.BEAN_QUALIFER)
public class ManuService implements IManuService {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.service.impl.ManuService";
	
	/**
	 * @see kr.smartfactory.platform.web.service.IManuService#selectManuList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Result<PaginationDTO<CompanyInfoDTO>> selectManuList(String name, String condition, String industry) {
		
		CompanyInfoDTO company1 = new CompanyInfoDTO();
		CompanyInfoDTO company2 = new CompanyInfoDTO();
		List<CompanyInfoDTO> list = new ArrayList<CompanyInfoDTO>();
		
		company1.setName("업체1");
		company1.setBusinessNumber("11");
		company1.setCeoName("대표자1");
		company1.setTelNumber("111-2222");
		company1.setAddress("업체 장소1");
		
		company2.setName("업체2");
		company2.setBusinessNumber("22");
		company2.setCeoName("대표자2");
		company2.setTelNumber("333-4444");
		company2.setAddress("업체 장소2");
		
		list.add(company1);
		list.add(company2);
		
		PaginationDTO<CompanyInfoDTO> pagination = new PaginationDTO<CompanyInfoDTO>(list, list.size());
		Result<PaginationDTO<CompanyInfoDTO>> res = new Result<>();
		
		if(pagination != null) {
			res.andTrue().setData(pagination);
		}
		else {
			res.andFalse().setMessage("목록 조회 실패");
		}
		
		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IManuService#selectCompanyUser(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Result<UserDTO> selectCompanyUser(Integer userType, String id) {
		
		UserInfoDTO userInfo = new UserInfoDTO();
		CompanyInfoDTO companyInfo = new CompanyInfoDTO();
		
		userInfo.setId("1");
		userInfo.setPassword("1");
		userInfo.setName("이름");
		userInfo.setEmail("yyh7750@gmail.com");
		userInfo.setTelNumber("111-1111");
		userInfo.setDepartment("개발1팀");
		userInfo.setRank("인턴");
		userInfo.setRegDate(20160613);
		
		companyInfo.setBusinessNumber(id);
		companyInfo.setName("회사명");
		companyInfo.setAddress("회사 주소");
		companyInfo.setCondition("업태");
		companyInfo.setIndustryType("업종");
		companyInfo.setTelNumber("222-2222");
		companyInfo.setFaxNumber("333333");
		companyInfo.setSiteUrl("www.home.co.kr");
		companyInfo.setCeoName("대표이름");
		
		UserDTO userDTO = new UserDTO(userInfo, companyInfo);
		
		Result<UserDTO> res = new Result<UserDTO>();
		
		res.andTrue().setData(userDTO);
		
		return res;
	}
	

}
