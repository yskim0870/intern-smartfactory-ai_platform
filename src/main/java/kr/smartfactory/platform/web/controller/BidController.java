package kr.smartfactory.platform.web.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.smartfactory.platform.web.dao.entity.bid.BidNoticeFile;
import kr.smartfactory.platform.web.dto.PaginationDTO;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import kr.smartfactory.platform.web.service.IBidService;
import kr.smartfactory.platform.web.service.impl.BidService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.controller.bid
 * @description : 입찰 조회에 대한 컨트롤러
 * @author : Younghun Yu
 * @date : 2021.12.23
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2021.12.23 Younghun Yu 최초 생성
 */
@Controller(BidController.BEAN_QUALIFER)
@RequestMapping(value = "/bids")
public class BidController {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.controller.BidController";
	
	private IBidService bidService;

	@Autowired
	public BidController(@Qualifier(BidService.BEAN_QUALIFER) IBidService bidService) {
		this.bidService = bidService;
	}

	/**
	 * @methodName : createBid
	 * @description : 입찰공고 등록을 위한 메소드
	 * @param bid
	 * @return : 등록 성공/실패에 대한 boolean 값
	 *
	 * @author : Younghun Yu
	 * @date : 2021.12.23
	 */
	@PostMapping(value = "")
	public ResponseEntity<Boolean> createBid(HttpServletRequest req, HttpServletResponse res, //
			@ModelAttribute BidDTO bid) {

		return ResponseEntity.ok(bidService.createBid(bid));
	}

	/**
	 * @methodName : fileDownload
	 * @description : 
	 * @param req
	 * @param res
	 * @param bidID
	 * @param fileID
	 *
	 * @author : Younghun Yu
	 * @date : 2022.02.03
	 */
	@GetMapping(value = "/download/{bidID}")
	public void fileDownload(HttpServletRequest req, HttpServletResponse res, //
			@PathVariable Integer bidID, //
			@RequestParam String fileID) {

		String path = "C:/Users/yyh77/Downloads/";
		String fileName = "";

		Result<List<BidNoticeFile>> fileList = bidService.selectFileList(bidID);
		
		if(fileList.getResult()) {
			for(BidNoticeFile file : fileList.getData()) {
				if(fileID.equalsIgnoreCase(file.getFileID())) {
					fileName = file.getFileName();
				}
			}
			path = String.join("", path, fileName);
		}

		try {
			byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			res.setHeader("Content-Transfer-Encoding", "binary");
			res.getOutputStream().write(fileByte);
			res.getOutputStream().flush();
			res.getOutputStream().close();
		} catch (IOException e) {
			System.out.printf("파일 다운로드 실패 : %s", e.getMessage());
		}

	}

	/**
	 * @methodName : selectCompanyName
	 * @description : 회사 아이디로 이름 조회
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.09
	 */
	@GetMapping(value = "/company/{id}")
	public ResponseEntity<Result<CompanyInfoDTO>> selectCompanyName(@PathVariable String id, HttpServletRequest request,
			HttpServletResponse response) {
		return ResponseEntity.ok(bidService.selectCompany(id));
	}

	/**
	 * @methodName : selectExpertList
	 * @description : 계약업체(전문업체) 조회
	 * @param request
	 * @param response
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.11
	 */
	@GetMapping(value = "/experts")
	public ResponseEntity<Result<List<String>>> selectExpertList(HttpServletRequest request,
			HttpServletResponse response) {
		return ResponseEntity.ok(bidService.selectExpertList());
	}

	/**
	 * @methodName : selectExpertManager
	 * @description : 계약 등록 시 셀렉트 박스로 계약업체 클릭할 경우 해당 계약업체 정보 조회
	 * @param companyName
	 * @param request
	 * @param response
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.01.09
	 */
	@GetMapping(value = "/contract/{companyName}")
	public ResponseEntity<Result<UserInfoDTO>> selectExpertManager(@PathVariable String companyName,
			HttpServletRequest request, HttpServletResponse response) {
		return ResponseEntity.ok(bidService.selectExpertManager(companyName));
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
	@GetMapping(value = "")
	public ResponseEntity<Result<PaginationDTO<BidDTO>>> selectBidList(//
			@RequestParam(required = false) Integer id//
			, @RequestParam(required = false) Long bidStartDate//
			, @RequestParam(required = false) Long bidEndDate//
			, @RequestParam(required = false) String bidName//
			, @RequestParam(required = false) String manufacturerName//
			, @RequestParam(required = false) Integer status//
			, @RequestParam Integer pageNum//
			, @RequestParam Integer pageItemPerPage//
			, @RequestParam(required = false) String orderby//
			, @RequestParam(required = false) Boolean desc//
			, HttpServletRequest request, HttpServletResponse response //
	) {
		return ResponseEntity.ok(bidService.selectBidList("bid", null, id, bidStartDate, bidEndDate, bidName, manufacturerName,
				status, pageNum, pageItemPerPage, orderby, desc));
	}
	
	/**
	 * @methodName : selectBidMgmtList
	 * @description : 입찰 관리 페이지 조회.
	 * @param userID : 사용자 아이디
	 * @param id : 공고 번호
	 * @param bidStartDate
	 * @param bidEndDate
	 * @param bidName
	 * @param manufacturerName
	 * @param status
	 * @param pageNum
	 * @param pageItemPerPage
	 * @param orderby
	 * @param desc
	 * @param request
	 * @param response
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.02.09
	 */
	@GetMapping(value = "/mgmt/{userID}")
	public ResponseEntity<Result<PaginationDTO<BidDTO>>> selectBidMgmtList(//
			@PathVariable String userID//
			, @RequestParam(required = false) Integer id//
			, @RequestParam(required = false) Long bidStartDate//
			, @RequestParam(required = false) Long bidEndDate//
			, @RequestParam(required = false) String bidName//
			, @RequestParam(required = false) String manufacturerName//
			, @RequestParam(required = false) Integer status//
			, @RequestParam Integer pageNum//
			, @RequestParam Integer pageItemPerPage//
			, @RequestParam(required = false) String orderby//
			, @RequestParam(required = false) Boolean desc//
			, HttpServletRequest request, HttpServletResponse response //
	) {
		return ResponseEntity.ok(bidService.selectBidList("bid", userID, id, bidStartDate, bidEndDate, bidName, manufacturerName,//
				status, pageNum, pageItemPerPage, orderby, desc));
	}
	
	@GetMapping(value = "/expert/{contractorID}")
	public ResponseEntity<Result<PaginationDTO<BidDTO>>> selectBidExpertList(//
			HttpServletRequest request, HttpServletResponse response//
			, @PathVariable String contractorID//
			, @RequestParam(required = false) String orderby//
			, @RequestParam(required = false) Boolean desc//
			, @RequestParam(required = false) Integer status//
			, @RequestParam Integer pageNum//
			, @RequestParam Integer pageItemPerPage//
	) {
		return ResponseEntity.ok(bidService.selectBidList("expert", contractorID, null, null, null, null, null,//
				status, pageNum, pageItemPerPage, orderby, desc));
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
	@GetMapping(value = "/{id}")
	public ResponseEntity<Result<BidDTO>> detailBid(@PathVariable Integer id, HttpServletRequest request,//
			HttpServletResponse response) {
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
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Boolean> updateBid(@RequestBody BidDTO bidInfo, HttpServletRequest request,
			HttpServletResponse response) {
		return new ResponseEntity<>(bidService.updateBid(bidInfo), HttpStatus.OK);
	}
}