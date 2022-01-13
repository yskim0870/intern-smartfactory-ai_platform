/**
 * 
 */
package kr.smartfactory.platform.web.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.smartfactory.platform.web.dao.entity.Company;
import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dao.entity.bid.BidInfo;
import kr.smartfactory.platform.web.dao.entity.bid.BidManagerInfo;
import kr.smartfactory.platform.web.dao.entity.bid.BidNoticeFile;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.bid.BidNoticeFileDTO;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.dao
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.27
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.27  Younghun Yu  최초 생성
 */
public interface IBidDao {
	
	/**
	 * @methodName : createBid
	 * @description : 
	 * @param bidInfo
	 * @param managerInfo
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.30
	 */
	public Result<Integer> createBid(BidInfo bidInfo, BidManagerInfo managerInfo);
	
	/**
	 * @methodName : uploadFile
	 * @description : 
	 * @param file
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.30
	 */
	public Integer uploadFile(Integer bidID, BidNoticeFile file);
	
	/**
	 * @methodName : selectCompany
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.09
	 */
	public Result<Company> selectCompany(String id);
	
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
	public Result<User> selectExpertManager(String companyName);
	
	/**
	 * @methodName : selectBidList
	 * @description : 
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
	 * @date : 2022.12.27
	 */
	public Result<List<BidDTO>> selectBidList(
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
	 * @methodName : selectAllCount
	 * @description : 
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.03
	 */
	public Integer selectAllCount();
	
	/**
	 * @methodName : selectDetailBid
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.27
	 */
	public Result<BidDTO> selectDetailBid(Integer id);
	
	/**
	 * @methodName : selectFileList
	 * @description : 
	 * @param id
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.28
	 */
	public List<BidNoticeFile> selectFileList(Integer id);
	
	/**
	 * @methodName : updateBid
	 * @description : 
	 * @param bid
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.31
	 */
	public Result<Integer> updateBid(BidDTO bid);
}