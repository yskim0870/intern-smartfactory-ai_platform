/**
 * 
 */
package kr.smartfactory.platform.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
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

}
