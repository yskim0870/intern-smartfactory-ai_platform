/**
 * 
 */
package kr.smartfactory.platform.web.sql.bid;

/**
 * @packageName : kr.smartfactory.platform.web.sql.bid
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.27
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.27  Younghun Yu  최초 생성
 */
public class BidQuery {

	/**
	 * 입찰 공고 정보 등록 -----------bidInfo------------- 
	 * id 공고번호 
	 * bid_name 공고명
	 * bid_start_date 공고시작날짜 
	 * bid_end_date 공고종료날짜 
	 * pre_price 예비 가격 
	 * vat_included 부가세 포함 여부
	 * 		0: 미포함
	 *  	1: 포함 
	 * status 계약상태 
	 * 		0: 대기중
	 *  	1: 진행중
	 *  	2: 계약완료
	 */
	public final static String INSERT_BID_INFO_QUERY = "" + "insert into "
			+ "bid_info(`id`, `bid_name`, `bid_start_date`, `bid_end_date`, `pre_price`, `vat_included`) "
			+ "values(?, ?, ?, ?, ?, ?)";

	/**
	 * 입찰 담당자 정보 등록 
	 * -----------bidManager------------- 
	 * bid_id 공고번호 
	 * manager_id 
	 * 제조사 ID
	 * name 담당자 이름 
	 * department 부서 
	 * rank 직책 
	 * tel_number 연락처 
	 * email 이메일
	 */
	public final static String INSERT_BID_MANAGER_QUERY = "" + "insert into "
			+ "bid_manager_info(`bid_id`, `manager_id`, `name`, `department`, `rank`, `tel_number`, `email`) "
			+ "values(?, ?, ?, ?, ?, ?, ?)";

	// 입찰 공고 목록 조회
	public final static String SELECT_BID_LIST_QUERY = "select bid_info.id, bid_info.bid_name, bid_info.bid_start_date, bid_info.bid_end_date, bid_info.status,\r\n"
			+ " bid_manager_info.manager_id, company_info.name"
			+ " from `bid_info`"
			+ " join `bid_manager_info` on (bid_info.id = bid_manager_info.bid_id)"
			+ " join `user_info` on (bid_manager_info.manager_id = user_info.id)"
			+ " join `company_info` on (user_info.business_number = company_info.business_number)"
			+ " where 1=1 {where_clause}";
	
	// 입찰 정보 상세보기
	public final static String SELECT_BID_DETAIL_QUERY = "select * from `bid_info`"
			+ " join `bid_manager_info` on (bid_info.id = bid_manager_info.bid_id)"
			+ " join `user_info` on (bid_manager_info.manager_id = user_info.id)"
			+ " join `company_info` on (user_info.business_number = company_info.business_number)"
			+ " where bid_id=?";

	// 입찰 공고 파일목록 읽어오기
	public final static String SELECT_BID_FILE_QUERY = "select * from `bid_notice_file` where bid_id=?";

	// 입찰공고 - 계약 등록
	public final static String UPDATE_BID_CONTRACT_QUERY = "update bid_info set "
			+ "contractor_id=?, "
			+ "contract_date=?, "
			+ "work_start_date=?, "
			+ "work_end_date=?, "
			+ "contract_price=?, "
			+ "vat_included=?, "
			+ "status=? "
			+ "where id=?";
}