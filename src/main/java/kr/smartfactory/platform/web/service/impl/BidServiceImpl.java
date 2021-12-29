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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import kr.smartfactory.platform.web.dao.IBidDao;
import kr.smartfactory.platform.web.dao.impl.BidDaoImpl;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.bid.BidInfoDTO;
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
@Service(BidServiceImpl.BEAN_QUALIFER)
public class BidServiceImpl implements IBidService {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.service.impl.BidServiceImpl";
	
	private IBidDao bidDao;

	@Autowired
	public BidServiceImpl(@Qualifier(BidDaoImpl.BEAN_QUALIFER) IBidDao bidDao) {
		this.bidDao = bidDao;
	}
	
	@Autowired
	PaginationImpl<BidDTO> bidPage;
	
	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#createBid(kr.smartfactory.platform.web.dto.bid.BidDTO)
	 */
	@Override
	public Boolean createBid(BidDTO bid) {

		Result<Integer> createResult = bidDao.createBid(bid);
		
		if (createResult.getResult()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IBidService#selectBidList(Integer,
	 *      Integer, Integer, String,
	 *      String, Integer, Integer, Integer, String,
	 *      Boolean)
	 */
	@Override
	public Result<PaginationDTO<BidDTO>> selectBidList(Integer id, Integer bidStartDate, Integer bidEndDate,
			String bidName, String manufacturerName, Integer status, Integer pageNum,
			Integer pageItemPerPage, String orderby, Boolean desc) {
		
		Result<List<BidDTO>> bidList = bidDao.selectBidList(bidEndDate, null, null, bidName, manufacturerName, status, pageNum, pageItemPerPage, orderby, desc);
		
		Result<PaginationDTO<BidDTO>> res = new Result<PaginationDTO<BidDTO>>();
		
		if(bidList.getResult()) {
			res.setResult(true);
			res.setData(bidPage.pagination(bidList.getData().toArray(new BidDTO[bidList.getData().size()]))); 
			
			return res;
		}
		else {
			res.setMessage(bidList.getMessage());
			res.setResult(false);
			
			return res;
		}
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
	public Boolean updateBid(BidInfoDTO bidInfo) {

		// TODO 계약일자에 대한 처리 - 눌렀을때의 시간을 기준으로 계약일자 설정
		Result<Integer> updateResult = bidDao.updateBid(bidInfo);

		if (updateResult.getResult()) {
			return true;
		}
		else {
			return false;
		}
	}
}