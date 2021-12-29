/**
 * 
 */
package kr.smartfactory.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.bid.BidInfoDTO;
import kr.smartfactory.platform.web.service.impl.BidServiceImpl;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.controller.bid
 * @description : 입찰 조회에 대한 컨트롤러
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
@Controller
@RequestMapping(value="/bids")
public class BidController {
	
	@Autowired
	private BidServiceImpl bidService;

	/**
	 * @methodName : createBid
	 * @description : 입찰공고 등록을 위한 메소드
	 * @param bid
	 * @return : 등록 성공/실패에 대한 boolean 값
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.23
	 */
	@PutMapping(value="")
	public ResponseEntity<Boolean> createBid(@RequestBody BidDTO bid){
		
		return new ResponseEntity<>(bidService.createBid(bid), HttpStatus.OK);
	}
	
	/**
	 * @methodName : selectBidList
	 * @description : 입찰 목록을 조회하기 위한 메소드
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
	 * @return : BidDTO의 object를 원소로 가진 item 배열과 totalCount
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.23
	 */
	@GetMapping(value="")
	public ResponseEntity<Result<PaginationDTO<BidDTO>>> selectBidList(
			@RequestParam(required = false) Integer id//
			,@RequestParam(required = false) Integer bidStartDate//
			,@RequestParam(required = false) Integer bidEndDate//
			,@RequestParam(required = false) String bidName//
			,@RequestParam(required = false) String manufacturerName//
			,@RequestParam(required = false) Integer status//
			,@RequestParam int pageNum//
			,@RequestParam int pageItemPerPage//
			,@RequestParam(required = false) String orderby//
			,@RequestParam(required = false) Boolean desc){
		
		return new ResponseEntity<>(bidService.selectBidList(
				pageItemPerPage, bidStartDate, bidEndDate, bidName, manufacturerName, 
				status, pageNum, pageItemPerPage, orderby, desc),
				HttpStatus.OK);
	}

	/**
	 * @methodName : detailBid
	 * @description : 입찰공고 상세보기를 위한 메소드
	 * @param id : 공고 번호
	 * @return : 상세보기에 들어갈 Object : BidDTO
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.23
	 */
	@GetMapping(value="/{id}")
	public ResponseEntity<Result<BidDTO>> detailBid(@PathVariable Integer id){
		return new ResponseEntity<>(bidService.detailBid(id), HttpStatus.OK);
	}
	
	/**
	 * @methodName : updateBid
	 * @description : 계약 등록(입찰공고문 수정)을 위한 메소드
	 * @param bidInfo : 계약에 대한 정보를 담은 Object : BidInfoDTO
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.23
	 */
	@PatchMapping(value="/{id}")
	public ResponseEntity<Boolean> updateBid(@RequestBody BidInfoDTO bidInfo){
		return new ResponseEntity<>(bidService.updateBid(bidInfo), HttpStatus.OK);
	}
}