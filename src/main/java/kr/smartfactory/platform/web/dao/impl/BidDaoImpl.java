/**
 * 
 */
package kr.smartfactory.platform.web.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import kr.smartfactory.platform.web.dao.IBidDao;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.bid.BidInfoDTO;
import kr.smartfactory.platform.web.dto.bid.BidNoticeFileDTO;
import kr.smartfactory.platform.web.sql.bid.BidQuery;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.dao.impl
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.29
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.29  Younghun Yu  최초 생성
 */
@Service(BidDaoImpl.BEAN_QUALIFER)
public class BidDaoImpl implements IBidDao {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.dao.impl.BidDaoImpl";

	// TODO 클래스로 따로 묶어서 상속받아 사용
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#createBid(kr.smartfactory.platform.web.dto.bid.BidDTO)
	 */
	@Override
	public Result<Integer> createBid(BidDTO bid) {

		int createCount = 0;
		Result<Integer> res = new Result<Integer>();

		try {
			Connection conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			try {

				createCount += jdbcTemplate.update(BidQuery.INSERT_BID_INFO_QUERY, bid.getBidInfo().getId(),
						bid.getBidInfo().getBidName(), bid.getBidInfo().getBidStartDate(),
						bid.getBidInfo().getBidEndDate(), bid.getBidInfo().getPrePrice(),
						bid.getBidInfo().getVatIncluded());

				createCount += jdbcTemplate.update(BidQuery.INSERT_BID_MANAGER_QUERY, bid.getManager().getBidID(),
						bid.getManager().getManagerID(), bid.getManager().getName(), bid.getManager().getDepartment(),
						bid.getManager().getRank(), bid.getManager().getTelNumber(), bid.getManager().getEmail());

				conn.commit();

				if (createCount == 2) {
					res.setResult(true);
					res.setData(createCount);
				}

				return res;
			} catch (SQLException e) {
				conn.rollback();

				res.setMessage("등록에 실패했습니다.\n" + e.getMessage());
				return res;
			}
		} catch (Exception e) {
			res.setMessage("등록에 실패했습니다.\n" + e.getMessage());
			return res;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectBidList(Integer, Long,
	 *      Long, String, String, Integer, Integer, Integer, String, Boolean)
	 */
	@Override
	public Result<List<BidDTO>> selectBidList(Integer id, Long bidStartDate, Long bidEndDate, String bidName,
			String manufacturerName, Integer status, Integer pageNum, Integer pageItemPerPage, String orderby,
			Boolean desc) {

		// where 절에 붙을 파라미터, 쿼리
		List<Object> params = new ArrayList<Object>();
		StringBuilder querySB = new StringBuilder();

		String query = BidQuery.SELECT_BID_LIST_QUERY;

		// 공고번호가 있다면
		if (id != null) {
			params.add(id);
			querySB.append("and id=?");
		}
		// 공고시작, 종료 날짜가 있다면
		if (bidStartDate != null && bidEndDate != null) {
			params.add(bidStartDate);
			params.add(bidEndDate);
			querySB.append(" and bidStartDate=? and bidEndDate=?");
		}
		// 공고명이 있다면
		if (bidName != null) {
			params.add(bidName);
			querySB.append(" and bidName=?");
		}
		// 제조사명이 있다면
		if (manufacturerName != null) {
			params.add(manufacturerName);
			querySB.append(" and manufacturerName=?");
		}
		// 계약상태가 있다면
		if (status != null) {
			params.add(status);
			querySB.append(" and status=?");
		}
		// 정렬기준이 있다면
		if (orderby != null && desc != null) {

			params.add(orderby);
			querySB.append(" order by ?");

			// 오름차순, 내림차순 true/false에 대한 처리
			if (desc) {
				querySB.append(" desc");
			} else {
				querySB.append(" asc");
			}

			params.add(orderby);
		}

		querySB.append(" limit ?");
		params.add(pageItemPerPage);
		querySB.append(" offset ?");
		params.add(pageNum);

		query = query.replace("{where_clause}", querySB.toString());

		List<BidDTO> bidList = jdbcTemplate.query(query, (rs, rowNum) -> new BidDTO(rs), params.toArray());

		Result<List<BidDTO>> res = new Result<List<BidDTO>>();

		if (bidList != null) {
			res.setData(bidList);
			res.setResult(true);

			return res;
		} else {
			res.setResult(false);
			res.setMessage("jdbc 오류발생");

			return res;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectDetailBid(int)
	 */
	@Override
	public Result<BidDTO> selectDetailBid(Integer id) {

		// 파일 목록 조회
		Result<List<BidNoticeFileDTO>> fileList = selectFileList(id);
		BidNoticeFileDTO files[] = new BidNoticeFileDTO[0];

		// 상세보기를 위한 Result 객체
		Result<BidDTO> res = new Result<BidDTO>();

		// 파일 정보를 제외한 나머지 데이터 조회
		BidDTO bid = jdbcTemplate.queryForObject(BidQuery.SELECT_BID_DETAIL_QUERY, (rs, rowNum) -> new BidDTO(rs), id);

		if (bid != null) {
			if (fileList.getResult()) {
				files = fileList.getData().toArray(new BidNoticeFileDTO[fileList.getData().size()]);

				res.setResult(true);
				res.setData(new BidDTO(bid, files));

				return res;
			} 
			else {
				res.setResult(false);
				res.setMessage(fileList.getMessage());

				return res;
			}
		}
		else {
			res.setResult(false);
			res.setMessage("상세보기에 필요한 데이터를 불러오지 못했습니다.");
			
			return res;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectFileList(int)
	 */
	@Override
	public Result<List<BidNoticeFileDTO>> selectFileList(Integer id) {
		List<BidNoticeFileDTO> fileList = jdbcTemplate.query(BidQuery.SELECT_BID_FILE_QUERY,
				(rs, rowNum) -> new BidNoticeFileDTO(rs), id);
		Result<List<BidNoticeFileDTO>> res = new Result<List<BidNoticeFileDTO>>();

		if (fileList != null) {
			res.setResult(true);
			res.setData(fileList);

			return res;
		} else {
			res.setResult(false);
			res.setMessage("File is null");

			return res;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#updateBid(kr.smartfactory.platform.web.dto.bid.BidInfoDTO)
	 */
	@Override
	public Result<Integer> updateBid(BidInfoDTO bidInfo) {
		
		int updateCount = jdbcTemplate.update(BidQuery.UPDATE_BID_CONTRACT_QUERY, bidInfo.getContractorID(),
				bidInfo.getContractDate(), bidInfo.getWorkStartDate(), bidInfo.getWorkEndDate(),
				bidInfo.getContractPrice(), bidInfo.getVatIncluded(), bidInfo.getStatus(), bidInfo.getId());
		
		Result<Integer> res = new Result<Integer>();
		
		if(updateCount == 1) {
			res.setData(updateCount);
			res.setResult(true);
			
			return res;
		}
		else {
			res.setMessage("업데이트 오류 발생");
			res.setResult(false);
			
			return res;
		}
	}
}