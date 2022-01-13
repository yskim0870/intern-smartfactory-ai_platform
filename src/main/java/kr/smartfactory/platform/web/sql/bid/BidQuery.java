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
	public final static String INSERT_BID_INFO_QUERY = String.join(" " //
			, "insert into" //
			, "bid_info("
			, "bid_info.bid_name, bid_info.bid_start_date, bid_info.bid_end_date,"
			, "bid_info.pre_price, bid_info.vat_included, bid_info.status)" //
			, "values(?, ?, ?, ?, ?, ?)");

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
	public final static String INSERT_BID_MANAGER_QUERY = String.join(" " //
			, "insert into" //
			, "bid_manager_info(" //
			, "bid_manager_info.bid_id, bid_manager_info.manager_id, bid_manager_info.name, bid_manager_info.department," //
			, "bid_manager_info.rank, bid_manager_info.tel_number, bid_manager_info.email)" //
			, "values(?, ?, ?, ?, ?, ?, ?)");
	
	// 입찰 공고 파일 등록
	public final static String INSERT_BID_FILE_QUERY = String.join(" " //
			, "insert into" //
			, "`bid_notice_file`(bid_notice_file.bid_id, bid_notice_file.file_id, bid_notice_file.file_type," //
			, "bid_notice_file.file_name, bid_notice_file.file_location)" //
			, "values(?, ?, ?, ?, ?)");

	// 입찰 공고 목록 조회
	public final static String SELECT_BID_LIST_QUERY = String.join(" " //
			, "select *" //
			, "from bid_info" //
			, "join bid_manager_info on (bid_info.id = bid_manager_info.bid_id)" //
			, "join user_info on (bid_manager_info.manager_id = user_info.id)" //
			, "join company_info on (user_info.business_number = company_info.business_number)" //
			, "where 1=1 {where_clause}");
	
	// 입찰 공고 목록 카운트
	public final static String SELECT_BID_LIST_COUNT_QUERY = String.join(" " //
			, "select count(*)" //
			, "from bid_info" //
			, "join bid_manager_info on (bid_info.id = bid_manager_info.bid_id)" //
			, "join user_info on (bid_manager_info.manager_id = user_info.id)" //
			, "join company_info on (user_info.business_number = company_info.business_number)");	
	
	// 입찰 정보 상세보기
	public final static String SELECT_BID_DETAIL_QUERY = String.join(" " //
			, "select distinct * from bid_info" //
			, "join bid_manager_info on (bid_info.id = bid_manager_info.bid_id)" //
			, "join user_info on (bid_manager_info.manager_id = user_info.id)" //
			, "join company_info on (user_info.business_number = company_info.business_number)" //
			, "where bid_info.id=?");
	
	// 입찰 공고 파일목록 읽어오기
	public final static String SELECT_BID_FILE_QUERY = "select * from `bid_notice_file` where bid_id=?";

	// 유저 아이디로 회사 정보 가져오기
	public final static String SELECT_COMPANY_NAME_TO_USER_ID = "select * from company_info join user_info on (company_info.business_number = user_info.business_number) where user_info.id = ?";
	
	// 계약업체만 조회하기
	public final static String SELECT_EXPERT_COMPANY_LIST = String.join(" " //
			, "select * from company_info" //
			, "join user_info on (company_info.business_number = user_info.business_number)" //
			, "join expert_status on (user_info.id = expert_status.user_id) where expert_status.status=1");
	
	// 입찰 계약 등록에 필요한 정보 조회 - 회사 이름으로 해당 회사 정보 가져오기
	public final static String SELECT_EXPERT_COMPANY_INFO = String.join(" "//
			, "select * from company_info" //
			, "join user_info on (company_info.business_number = user_info.business_number)" // 
			, "join expert_status on (user_info.id = expert_status.user_id)" //
			, "where company_info.name=? and expert_status.status=1");
			
	// 입찰공고 - 계약 등록
	public final static String UPDATE_BID_CONTRACT_QUERY = String.join(" " //
			, "update bid_info set" //
			, "bid_info.contract_date=?," //
			, "bid_info.contractor_id=?," //
			, "bid_info.work_start_date=?," //
			, "bid_info.work_end_date=?," //
			, "bid_info.contract_price=?," //
			, "bid_info.status=2"
			, "where bid_info.id=?");
}