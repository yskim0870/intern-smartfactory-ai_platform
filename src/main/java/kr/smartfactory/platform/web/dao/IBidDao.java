/**
 * 
 */
package kr.smartfactory.platform.web.dao;

import java.util.List;

import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.bid.BidInfoDTO;
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
	 * @param bid
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.27
	 */
	public Result<Integer> createBid(BidDTO bid);
	
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
	public Result<List<BidNoticeFileDTO>> selectFileList(Integer id);
	
	/**
	 * @methodName : updateBid
	 * @description : 
	 * @param bidInfo
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.27
	 */
	public Result<Integer> updateBid(BidInfoDTO bidInfo);
}