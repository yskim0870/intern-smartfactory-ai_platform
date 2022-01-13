/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:32
*/

/**
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:32
*/
package kr.smartfactory.platform.web.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.smartfactory.platform.web.dao.IBidDao;
import kr.smartfactory.platform.web.dao.entity.Company;
import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dao.entity.bid.BidInfo;
import kr.smartfactory.platform.web.dao.entity.bid.BidManagerInfo;
import kr.smartfactory.platform.web.dao.entity.bid.BidNoticeFile;
import kr.smartfactory.platform.web.dao.impl.BidDao;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.bid.BidNoticeFileDTO;
import kr.smartfactory.platform.web.dto.bid.SampleFileDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import kr.smartfactory.platform.web.service.IBidService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.service.impl
 * @description : 입찰관련 서비스를 수행하기 위한 클래스 : 목록조회, 상세보기, 계약등록
 * @author : Younghun Yu
 * @date : 2021.12.24
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2021.12.24 Younghun Yu 최초 생성
 */
@Service(BidService.BEAN_QUALIFER)
public class BidService implements IBidService {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.service.impl.BidService";
	
	private IBidDao bidDao;

	@Autowired
	public BidService(@Qualifier(BidDao.BEAN_QUALIFER) IBidDao bidDao) {
		this.bidDao = bidDao;
	}
	
	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#createBid(kr.smartfactory.platform.web.dto.bid.BidDTO)
	 */
	@Override
	public Boolean createBid(SampleFileDTO files, BidDTO bid) {
		
		Integer bidID = bidDao.selectAllCount() + 1;
		bid.getBidInfo().setId(bidID);
		bid.getManager().setBidID(bidID);
		
		BidInfo bidInfo = new BidInfo(bid.getBidInfo());
		BidManagerInfo managerInfo = new BidManagerInfo(bid.getManager());

		Result<Integer> createResult = bidDao.createBid(bidInfo, managerInfo);

		// file 처리
		BidNoticeFile upload = null;
		Integer uploadFileCount = 0;
		List<BidNoticeFile> list = new ArrayList<>();
//		for (MultipartFile file : files.getFiles()) {
//	        if (!file.isEmpty()) {
//	            // UUID를 이용해 unique한 파일 이름을 만들어준다.
//	        	upload = new BidNoticeFile(UUID.randomUUID().toString(), file.getOriginalFilename());
//	            list.add(upload);
//
//	            File newFileName = new File(upload.getFileID() + "_" + upload.getFileName());
//	            // 전달된 내용을 실제 물리적인 파일로 저장해준다.
//	            try {
//					file.transferTo(newFileName);
//				} catch (IllegalStateException | IOException e) {
//					System.out.printf("\n\nError : %s\n\n", e.getMessage());
//				}
//	            
//	            uploadFileCount = bidDao.uploadFile(bid.getBidInfo().getId(), upload);
//	        }
//	    }
		
		if (createResult.getResult()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#selectCompanyName(java.lang.String)
	 */
	@Override
	public Result<CompanyInfoDTO> selectCompany(String id) {
		Result<Company> company = bidDao.selectCompany(id);
		Result<CompanyInfoDTO> companyInfo = new Result<CompanyInfoDTO>();
		if(!company.getResult()) {
			return companyInfo.andFalse().setMessage("회사명 조회 실패");
		}
		companyInfo = new Result<>(new CompanyInfoDTO(company));
		
		return companyInfo;
	}
	
	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#selectExpertManager(java.lang.String)
	 */
	@Override
	public Result<UserInfoDTO> selectExpertManager(String companyName) {
		
		Result<User> expertManager = bidDao.selectExpertManager(companyName);
		Result<UserInfoDTO> res = new Result<UserInfoDTO>();
		
		if(expertManager.getResult()) {
			res.andTrue().setData(new UserInfoDTO(expertManager.getData()));
		}
		else {
			res.andFalse().setMessage(expertManager.getMessage());
		}
		
		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#selectBidList(Integer,
	 *      Integer, Integer, String,
	 *      String, Integer, Integer, Integer, String,
	 *      Boolean)
	 */
	@Override
	public Result<PaginationDTO<BidDTO>> selectBidList(Integer id, Long bidStartDate, Long bidEndDate,
			String bidName, String manufacturerName, Integer status, Integer pageNum,
			Integer pageItemPerPage, String orderby, Boolean desc) {
		
		Result<List<BidDTO>> bidList = bidDao.selectBidList(id, bidStartDate, bidEndDate, bidName, manufacturerName, status, pageNum, pageItemPerPage, orderby, desc);
		
		Result<PaginationDTO<BidDTO>> res = new Result<PaginationDTO<BidDTO>>();
		Integer count = bidDao.selectAllCount();
		
		PaginationDTO<BidDTO> pagination = new PaginationDTO<BidDTO>(bidList.getData(), count);
		
		if(bidList.getResult()) {
			res.andTrue().setData(pagination);
		}
		else {
			res.andFalse().setMessage(bidList.getMessage());
		}
		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#detailBid(Integer)
	 */
	@Override
	public Result<BidDTO> detailBid(Integer id) {
		
		Result<BidDTO> detailBid = bidDao.selectDetailBid(id);
		Result<BidDTO> res = new Result<BidDTO>();
		
		if(detailBid.getResult()) {
			res.setData(detailBid.getData());
			res.setResult(true);
			
			return res;
		}
		else {
			res.setMessage(detailBid.getMessage());
			res.setResult(false);
			
			return res;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#updateBid(kr.smartfactory.platform.web.dto.bid.BidInfoDTO)
	 */
	@Override
	public Boolean updateBid(BidDTO bidInfo) {

		Result<Integer> updateResult = bidDao.updateBid(bidInfo);
		
		if (updateResult.getResult()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#selectExpertList()
	 */
	@Override
	public Result<List<String>> selectExpertList() {
		
		Result<List<String>> res = bidDao.selectExpertList();
		
		return res;
	}
}