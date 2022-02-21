/**
 * 
 */
package kr.smartfactory.platform.web.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import open.commons.Result;

import kr.smartfactory.platform.web.dao.IBidDao;
import kr.smartfactory.platform.web.dao.entity.Company;
import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dao.entity.bid.BidInfo;
import kr.smartfactory.platform.web.dao.entity.bid.BidManagerInfo;
import kr.smartfactory.platform.web.dao.entity.bid.BidNoticeFile;
import kr.smartfactory.platform.web.dto.bid.BidDTO;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;
import kr.smartfactory.platform.web.sql.bid.BidQuery;

/**
 * @packageName : kr.smartfactory.platform.web.dao.impl
 * @description :
 * @author : Younghun Yu
 * @date : 2022.01.10
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2022.01.10 Younghun Yu 정렬기준 작성
 */
@Repository(BidDao.BEAN_QUALIFER)
public class BidDao extends DBGenericDao implements IBidDao {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.dao.impl.BidDao";

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#createBid(kr.smartfactory.platform.web.dto.bid.BidDTO)
	 */
	@Override
	public Result<Integer> createBid(BidInfo bidInfo, BidManagerInfo managerInfo) {

		int createCount = 0;
		Result<Integer> res = new Result<Integer>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			createCount += jdbcTemplate.update(BidQuery.INSERT_BID_INFO_QUERY //
					, bidInfo.getBidName(), bidInfo.getBidStartDate(), bidInfo.getBidEndDate() //
					, bidInfo.getPrePrice(), bidInfo.getVatIncluded(), bidInfo.getStatus());

			createCount += jdbcTemplate.update(BidQuery.INSERT_BID_MANAGER_QUERY //
					, bidInfo.getId(), managerInfo.getManagerID(), managerInfo.getName(), managerInfo.getDepartment() //
					, managerInfo.getRank(), managerInfo.getTelNumber(), managerInfo.getEmail());

			conn.commit();

			if (createCount == 2) {
				res.andTrue().setData(createCount);
			}

			return res;

		} catch (Exception e) {

			res.andFalse().setMessage("등록에 실패했습니다.\n" + e.getMessage());

			try {
				conn.rollback();
			} catch (SQLException sqlException) {
				res.andFalse().setMessage(sqlException.getMessage());
			}
			return res;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#uploadFile(kr.smartfactory.platform.web.dao.entity.bid.BidNoticeFile)
	 */
	@Override
	public Integer uploadFile(Integer bidID, BidNoticeFile file) {
		return jdbcTemplate.update(BidQuery.INSERT_BID_FILE_QUERY, bidID, file.getFileID(), file.getFileType(),
				file.getFileName(), file.getFileLocation());
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectCompanyName(java.lang.String)
	 */
	@Override
	public Result<Company> selectCompany(String id) {
		Result<Company> res = new Result<Company>();
		try {
			Company company = jdbcTemplate.queryForObject(BidQuery.SELECT_COMPANY_NAME_TO_USER_ID,
					(rs, rowNum) -> new Company(rs), id);
			if (company != null) {
				res.andTrue().setData(company);
			} else {
				res.andFalse().setMessage("회사명 조회 실패");
			}

			return res;
		} catch (EmptyResultDataAccessException e) {
			return res.andFalse().setMessage("회사명 조회 실패");
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectExpertManager(java.lang.String)
	 */
	@Override
	public Result<User> selectExpertManager(String companyName) {

		User expertManager = jdbcTemplate.queryForObject(BidQuery.SELECT_EXPERT_COMPANY_INFO,
				(rs, rowNum) -> new User(rs), companyName);

		Result<User> res = new Result<>();

		if (expertManager != null) {
			res.andTrue().setData(expertManager);
		} else {
			res.andFalse().setMessage("계약업체 조회 실패");
		}

		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectBidList(Integer, Long,
	 *      Long, String, String, Integer, Integer, Integer, String, Boolean)
	 */
	@Override
	public Result<List<BidDTO>> selectBidList(String url, String userID, Integer id, Long bidStartDate, Long bidEndDate,
			String bidName, String manufacturerName, Integer status, Integer pageNum, Integer pageItemPerPage,
			String orderby, Boolean desc) {

		// where 절에 붙을 파라미터, 쿼리
		List<Object> params = new ArrayList<Object>();
		StringBuilder querySB = new StringBuilder();

		String query = null;

		if (url == "bid") {

			query = BidQuery.SELECT_BID_LIST_QUERY;

			// userID가 있다면 (입찰관리 해당 제조사만 표시)
			if (userID != null) {
				params.add(userID);
				querySB.append(" and user_info.id=?");
			}
			// 공고번호가 있다면
			if (id != null) {
				params.add(String.join("", "%", id.toString(), "%"));
				querySB.append(" and bid_info.id like ?");
			}
			// 공고시작, 종료 날짜가 있다면
			if (bidStartDate != null) {
				params.add(bidStartDate);
				params.add(bidStartDate);
				querySB.append(" and ? <= bid_info.bid_end_date and ? >= bid_info.bid_start_date");
			}
			if (bidEndDate != null) {
				params.add(bidEndDate);
				params.add(bidEndDate);
				querySB.append(" and bid_info.bid_start_date <= ? and bid_info.bid_end_date >= ?");
			}
			// 공고명이 있다면
			if (bidName != null) {
				params.add(String.join("", "%", bidName, "%"));
				querySB.append(" and bid_info.bid_name like ?");
			}
			// 제조사명이 있다면
			if (manufacturerName != null) {
				params.add(String.join("", "%", manufacturerName, "%"));
				querySB.append(" and company_info.name like ?");
			}
			// 계약상태가 있다면
			if (status != null) {
				params.add(status);
				querySB.append(" and bid_info.status=?");
			}
			// 정렬기준이 있다면
			if (orderby != null) {
				querySB.append(" order by ");

				// 오름차순, 내림차순 true/false에 대한 처리
				if (desc) {
					querySB.append(String.join(" ", orderby, "desc"));
				} else {
					querySB.append(String.join(" ", orderby, "asc"));
				}
			}
			// limit에 쓰일 변수
			if (pageItemPerPage != null) {
				querySB.append("and pageItemPerPage=?");
				params.add(pageItemPerPage);
			}
			// offset에 쓰일 변수
			if (pageNum != null) {
				querySB.append("and pageNum=?");

				if (pageItemPerPage == 15) {
					pageNum = (pageNum - 1) * 15;
				} else if (pageItemPerPage == 30) {
					pageNum = (pageNum - 1) * 30;
				}

				params.add(pageNum);
			}

			query = query.replace("{where_clause}", querySB.toString());
			query = query.replace("and pageItemPerPage=", " limit ");
			query = query.replace("and pageNum=", " offset ");
		} //
		else if (url == "expert") {
			query = String.join(" "//
					, "select * from bid_info"
					, "join bid_manager_info on (bid_info.id = bid_manager_info.bid_id)"
					, "join user_info on (bid_manager_info.manager_id = user_info.id)"
					, "join company_info on (user_info.business_number = company_info.business_number)"
					, "where bid_info.contractor_id=?");
			
					params.add(userID);
		}

		List<BidDTO> bidList = jdbcTemplate.query(query, (rs, rowNum) -> new BidDTO(rs), params.toArray());

		Result<List<BidDTO>> res = new Result<List<BidDTO>>();

		if (bidList != null) {
			res.andTrue().setData(bidList);
		} else {
			res.andFalse().setMessage("jdbc 오류발생");
		}
		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectDetailBid(int)
	 */
	@Override
	public Result<BidDTO> selectDetailBid(Integer id) {

		// 상세보기를 위한 Result 객체
		Result<BidDTO> res = new Result<BidDTO>();

		Connection conn = null;
		BidDTO bid = null;
		CompanyInfoDTO contractComapny = null;
		UserInfoDTO contractor = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			try {
				// 파일 정보를 제외한 나머지 데이터 조회
				bid = jdbcTemplate.queryForObject(BidQuery.SELECT_BID_DETAIL_QUERY, (rs, rowNum) -> new BidDTO(rs), id);

				contractComapny = jdbcTemplate.queryForObject(BidQuery.SELECT_COMPANY_NAME_TO_USER_ID, //
						(rs, rowNum) -> new CompanyInfoDTO(rs), bid.getBidInfo().getContractorID());
				contractor = jdbcTemplate.queryForObject(BidQuery.SELECT_CONTRACT_INFO, //
						(rs, rowNum) -> new UserInfoDTO(rs), bid.getBidInfo().getContractorID(), id);
				contractor.setCompanyInfo(contractComapny);

			} catch (EmptyResultDataAccessException e) {
			}
			bid.setContractor(contractor);

			// file 정보 조회
			List<BidNoticeFile> fileList = jdbcTemplate.query(BidQuery.SELECT_BID_FILE_QUERY,
					(rs, rowNum) -> new BidNoticeFile(rs), id);

			if (fileList != null) {
				bid.setFileList(fileList);
			}

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException exception) {
				exception.printStackTrace();
				System.out.println(exception.getMessage());
			}
		}

		if (bid != null) {
			res.andTrue().setData(bid);
		} else {
			res.andFalse().setMessage("상세보기에 필요한 데이터를 불러오지 못했습니다.");
		}
		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectFileList(int)
	 */
	@Override
	public List<BidNoticeFile> selectFileList(Integer id) {
		List<BidNoticeFile> fileList = jdbcTemplate.query(BidQuery.SELECT_BID_FILE_QUERY,
				(rs, rowNum) -> new BidNoticeFile(rs), id);

		if (fileList != null) {
			return fileList;
		} else {
			return null;
		}
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#updateBid(kr.smartfactory.platform.web.dto.bid.BidInfoDTO)
	 */
	@Override
	public Result<Integer> updateBid(BidDTO bid) {

		int updateCount = jdbcTemplate.update(BidQuery.UPDATE_BID_CONTRACT_QUERY, bid.getBidInfo().getContractDate(),
				bid.getBidInfo().getContractorID(), bid.getBidInfo().getWorkStartDate(),
				bid.getBidInfo().getWorkEndDate(), bid.getBidInfo().getContractPrice(), bid.getBidInfo().getId());

		Result<Integer> res = new Result<Integer>();

		if (updateCount == 1) {
			res.andTrue().setData(updateCount);
		} else {
			res.andFalse().setMessage("업데이트 오류 발생");
		}
		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectAllCount()
	 */
	@Override
	public Integer selectAllCount() {
		return jdbcTemplate.queryForObject(BidQuery.SELECT_BID_LIST_COUNT_QUERY, int.class);
	}

	/**
	 * @see kr.smartfactory.platform.web.dao.IBidDao#selectExpertList()
	 */
	@Override
	public Result<List<String>> selectExpertList() {
		List<Company> expertInfo = jdbcTemplate.query(BidQuery.SELECT_EXPERT_COMPANY_LIST,
				(rs, rowNum) -> new Company(rs));

		Result<List<String>> res = new Result<>();
		List<String> expertList = new ArrayList<String>();

		if (expertInfo != null) {
			for (Company expert : expertInfo) {
				expertList.add(expert.getName());
			}
			res.andTrue().setData(expertList);
		} else {
			res.andFalse().setMessage("목록 조회 실패");
		}

		return res;
	}
}