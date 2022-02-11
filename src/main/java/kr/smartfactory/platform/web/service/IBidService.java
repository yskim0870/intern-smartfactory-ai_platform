/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:14
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
 * @since: 2021. 12. 8. 오전 9:55:14
*/
package kr.smartfactory.platform.web.service;

import java.util.List;

import kr.smartfactory.platform.web.dao.entity.bid.BidNoticeFile;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.bid.BidNoticeFileDTO;
import kr.smartfactory.platform.web.dto.bid.SampleFileDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import open.commons.Result;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 12. 8. 오전 9:55:14
 *
 */
public interface IBidService {

	/**
	 * @methodName : createBid
	 * @description : 
	 * @param bid
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public Boolean createBid(BidDTO bid);
	
	/**
	 * @methodName : selectCompany
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.09
	 */
	public Result<CompanyInfoDTO> selectCompany(String id);
	
	/**
	 * @methodName : selectFileList
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.14
	 */
	public Result<List<BidNoticeFile>> selectFileList(Integer id);
	
	/**
	 * @methodName : selectExpertList
	 * @description : 
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.09
	 */
	public Result<List<String>> selectExpertList();
	
	/**
	 * @methodName : selectExpertManager
	 * @description : 
	 * @param companyName
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.09
	 */
	public Result<UserInfoDTO> selectExpertManager(String companyName);
	
	/**
	 * @methodName : selectBidList
	 * @description :
	 * @param url : 입찰조회,관리와 도메인업체관리 페이지의 조회기능을 구분 짓기 위해 추가. 
	 * @param userID : 입찰관리 페이지-제조사 아이디, 도메인IT전문업체관리 페이지-도메인IT전문업체 아이디
	 * @param id
	 * @param bidStartDate
	 * @param bidEndDate
	 * @param bidName
	 * @param manufacturerName
	 * @param status
	 * @param pageNum
	 * @param pageItemPerPage
	 * @param orderby
	 * @param desc
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.09
	 */
	public Result<PaginationDTO<BidDTO>> selectBidList(
			String url,
			String userID,
			Integer id,
			Long bidStartDate,
			Long bidEndDate, 
			String bidName,
			String manufacturerName, 
			Integer status,
			Integer pageNum, 
			Integer pageItemPerPage,
			String orderby, 
			Boolean desc);
	
	/**
	 * @methodName : detailBid
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.24
	 */
	public Result<BidDTO> detailBid(Integer id);
	
	/**
	 * @methodName : updateBid
	 * @description : 
	 * @param bidInfo
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.31
	 */
	public Boolean updateBid(BidDTO bidInfo);
}
