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
import kr.smartfactory.platform.web.dto.bid.BidInfoDTO;
import kr.smartfactory.platform.web.dto.bid.BidManagerDTO;
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
	 * @methodName : uploadFile
	 * @description :
	 * @param file
	 * @param upload
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.14
	 */
	public void uploadFile(List<BidNoticeFile> list, MultipartFile file, BidNoticeFile upload) {

		File newFileName = new File(upload.getFileName());
		try {
			// 전달된 내용을 실제 물리적인 파일로 저장해준다.
			file.transferTo(newFileName);
		} catch (IllegalStateException | IOException e) {
			System.out.printf("\n\nError : %s\n\n", e.getMessage());
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#createBid(kr.smartfactory.platform.web.dto.bid.BidDTO)
	 */
	@Override
	public Boolean createBid(BidDTO bid) {

		// 컨트롤러에서 받아온 데이터 설정
		bid.setBidInfo(new BidInfoDTO(bid.getsBidInfo()));
		bid.setManager(new BidManagerDTO(bid.getsManager()));
		BidInfo bidInfo = new BidInfo(bid.getBidInfo());
		BidManagerInfo managerInfo = new BidManagerInfo(bid.getManager());

		// auto increment로 만들어진 공고번호 설정
		Integer bidID = bidDao.selectAllCount() + 1;
		bidInfo.setId(bidID);
		managerInfo.setBidID(bidID);
		bid.getBidInfo().setId(bidID);

		Result<Integer> createResult = bidDao.createBid(bidInfo, managerInfo);

		// file 처리
		BidNoticeFile upload = null;
		Integer uploadFileCount = 0;
		List<BidNoticeFile> list = new ArrayList<>();

		String path = "C:\\Users\\yyh77\\Downloads";
		// 입찰공고문 파일 업로드 및 데이터베이스에 정보 저장
		if (bid.getBidFiles() != null) {
			for (MultipartFile file : bid.getBidFiles()) {
				if (!file.isEmpty()) {
					// fileID: UUID, fileName: OriginalFileName, fileType, filePath
					upload = new BidNoticeFile(UUID.randomUUID().toString(), file.getOriginalFilename(), 0, path);
					list.add(upload);
					uploadFile(list, file, upload);
					uploadFileCount += bidDao.uploadFile(bid.getBidInfo().getId(), upload);
				}
			}
		}

		// 샘플데이터 파일 업로드 및 데이터베이스에 정보 저장
		if (bid.getSampleFiles() != null) {
			for (MultipartFile file : bid.getSampleFiles()) {
				if (!file.isEmpty()) {
					// fileID: UUID, fileName: OriginalFileName, fileType, filePath
					upload = new BidNoticeFile(UUID.randomUUID().toString(), file.getOriginalFilename(), 1, path);
					list.add(upload);
					uploadFile(list, file, upload);
					uploadFileCount += bidDao.uploadFile(bid.getBidInfo().getId(), upload);
				}
			}
		}

		// null exception을 방지하기 위한 변수
		int bidLength = bid.getBidFiles() == null ? 0 : bid.getBidFiles().length;
		int sampleLength = bid.getSampleFiles() == null ? 0 : bid.getSampleFiles().length;
		
		if (createResult.getResult() && uploadFileCount	== (bidLength + sampleLength)) {
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
		if (!company.getResult()) {
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

		if (expertManager.getResult()) {
			res.andTrue().setData(new UserInfoDTO(expertManager.getData()));
		} else {
			res.andFalse().setMessage(expertManager.getMessage());
		}

		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#selectBidList(java.lang.String,
	 *      java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public Result<PaginationDTO<BidDTO>> selectBidList(//
			String url, String userID, Integer id, Long bidStartDate, Long bidEndDate, //
			String bidName, String manufacturerName, Integer status, Integer pageNum, //
			Integer pageItemPerPage, String orderby, Boolean desc//
	) {

		Result<PaginationDTO<BidDTO>> res = null;

		if (url == "bid") {
			Result<List<BidDTO>> bidList = bidDao.selectBidList(url, userID, id, bidStartDate, bidEndDate, bidName,
					manufacturerName, status, pageNum, pageItemPerPage, orderby, desc);

			res = new Result<PaginationDTO<BidDTO>>();
			Integer count = bidDao.selectAllCount();

			PaginationDTO<BidDTO> pagination = new PaginationDTO<BidDTO>(bidList.getData(), count);

			if (bidList.getResult()) {
				res.andTrue().setData(pagination);
			} else {
				res.andFalse().setMessage(bidList.getMessage());
			}
		} //
		else if (url == "expert") {
			Result<List<BidDTO>> bidList = bidDao.selectBidList(url, userID, id, bidStartDate, bidEndDate, bidName,
					manufacturerName, status, pageNum, pageItemPerPage, orderby, desc);

			res = new Result<PaginationDTO<BidDTO>>();
			Integer count = bidDao.selectAllCount();

			PaginationDTO<BidDTO> pagination = new PaginationDTO<BidDTO>(bidList.getData(), count);

			if (bidList.getResult()) {
				res.andTrue().setData(pagination);
			} else {
				res.andFalse().setMessage(bidList.getMessage());
			}
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

		if (detailBid.getResult()) {
			res.setData(detailBid.getData());
			res.setResult(true);

			return res;
		} else {
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
		} else {
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

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#selectFileList(java.lang.Integer)
	 */
	@Override
	public Result<List<BidNoticeFile>> selectFileList(Integer id) {

		List<BidNoticeFile> fileList = bidDao.selectFileList(id);

		Result<List<BidNoticeFile>> res = new Result<List<BidNoticeFile>>();

		if (fileList != null) {
			res.andTrue().setData(fileList);
		} else {
			res.andFalse().setMessage("파일 조회 실패!");
		}

		return res;
	}
}