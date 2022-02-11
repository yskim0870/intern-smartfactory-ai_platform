/**
 * 
 */
package kr.smartfactory.platform.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.common.CertificateDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.ExpertStatusDTO;
import kr.smartfactory.platform.web.dto.common.UserDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import kr.smartfactory.platform.web.service.ICompanyService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.service.impl
 * @description :
 * @author : Younghun Yu
 * @date : 2022.01.26
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2022.01.26 Younghun Yu 최초 생성
 */
@Service(CompanyService.BEAN_QUALIFER)
public class CompanyService implements ICompanyService {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.service.impl.ManuService";

	/**
	 * @see kr.smartfactory.platform.web.service.ICompanyService#selectManuList(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Result<PaginationDTO<UserDTO>> selectCompanyList(//
			Integer userType, //
			String name, //
			String condition, //
			String industry, //
			Integer status//
	) {

		Result<PaginationDTO<UserDTO>> res = new Result<>();

		// 제조사 목록 조회
		if (userType == 1) {
			CompanyInfoDTO manu1 = new CompanyInfoDTO();
			CompanyInfoDTO manu2 = new CompanyInfoDTO();
			List<UserDTO> manuList = new ArrayList<UserDTO>();

			manu1.setName("업체1");
			manu1.setBusinessNumber("11");
			manu1.setCeoName("대표자1");
			manu1.setTelNumber("111-2222");
			manu1.setAddress("업체 장소1");

			manu2.setName("업체2");
			manu2.setBusinessNumber("22");
			manu2.setCeoName("대표자2");
			manu2.setTelNumber("333-4444");
			manu2.setAddress("업체 장소2");

			UserDTO user1 = new UserDTO();
			UserDTO user2 = new UserDTO();
			user1.setCompanyInfo(manu1);
			user2.setCompanyInfo(manu2);

			manuList.add(user1);
			manuList.add(user2);

			PaginationDTO<UserDTO> pagination = new PaginationDTO<UserDTO>(manuList, manuList.size());

			if (pagination != null) {
				res.andTrue().setData(pagination);
			}
		} 
		// 도메인 전문업체 목록 조회
		else if (userType == 2) {

			UserDTO expert1 = new UserDTO();
			CompanyInfoDTO company1 = new CompanyInfoDTO();
			UserInfoDTO user1 = new UserInfoDTO();
			ExpertStatusDTO expertInfo1 = new ExpertStatusDTO();

			company1.setName("전문업체명1");
			company1.setTelNumber("111-1111");
			expert1.setCompanyInfo(company1);
			user1.setName("전문가명1");
			user1.setEmail("yyh7750@gmail.com");
			user1.setEtcInfo("1111-1111");
			expertInfo1.setUserID("qwer");
			expertInfo1.setStatus(1);
			expert1.setUserInfo(user1);
			expert1.setExpertInfo(expertInfo1);

			UserDTO expert2 = new UserDTO();
			CompanyInfoDTO company2 = new CompanyInfoDTO();
			UserInfoDTO user2 = new UserInfoDTO();
			ExpertStatusDTO expertInfo2 = new ExpertStatusDTO();
			
			company2.setName("전문업체명2");
			company2.setTelNumber("222-2222");
			expert2.setCompanyInfo(company2);
			user2.setName("전문가명2");
			user2.setEmail("yyh7750@gmail.com");
			user2.setEtcInfo("2222-2222");
			expertInfo2.setUserID("qwer");
			expertInfo2.setStatus(0);
			expert2.setUserInfo(user2);
			expert2.setExpertInfo(expertInfo2);

			List<UserDTO> expertList = new ArrayList<UserDTO>();

			expertList.add(expert1);
			expertList.add(expert2);

			PaginationDTO<UserDTO> pagination = new PaginationDTO<UserDTO>(expertList, expertList.size());

			if (pagination != null) {
				res.andTrue().setData(pagination);
			}
		}

		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.ICompanyService#selectCompanyUser(java.lang.Integer,
	 *      java.lang.String)
	 */
	@Override
	public Result<UserDTO> selectCompanyUser(Integer userType, String id) {

		Result<UserDTO> res = null;

		// 제조사 관리 상세보기
		if (userType == 1) {
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

			UserDTO userDTO = new UserDTO(userInfo, companyInfo, null, null);

			res = new Result<UserDTO>();

			res.andTrue().setData(userDTO);
		}

		// 도메인 전문업체 상세보기
		else if (userType == 2) {
			UserInfoDTO userInfo = new UserInfoDTO();
			
			userInfo.setName("전문가");
			userInfo.setEtcInfo("1234-5678");
			userInfo.setTelNumber("888-8888-8888");
			userInfo.setRank("사장");
			userInfo.setDepartment("회사");
			userInfo.setEmail("qqq@aaaa.com");
			
			List<CertificateDTO> certificates = new ArrayList<CertificateDTO>();
			CertificateDTO certificate1 = new CertificateDTO();
			CertificateDTO certificate2 = new CertificateDTO();
			
			certificate1.setCertificateID("12345");
			certificate1.setCertificateType("정보처리기사");
			certificate1.setUserID("qwer");
			certificate1.setAcquisitionDate((long) 1234567789);
			certificates.add(certificate1);
			
			certificate2.setCertificateID("67899");
			certificate2.setCertificateType("컴활");
			certificate2.setUserID("qwer");
			certificate2.setAcquisitionDate((long) 324567789);
			certificates.add(certificate2);
			
			UserDTO userDTO = new UserDTO(userInfo, null, certificates, null);

			res = new Result<UserDTO>();

			res.andTrue().setData(userDTO);
		}

		return res;
	}
}