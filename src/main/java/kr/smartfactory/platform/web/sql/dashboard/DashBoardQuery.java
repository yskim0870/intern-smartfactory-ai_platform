/**
 * 
 */
package kr.smartfactory.platform.web.sql.dashboard;

/**
 * @packageName : kr.smartfactory.platform.web.dao
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.24
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.24  Younghun Yu  최초 생성
 */
public class DashBoardQuery {
	
	// 입찰 공고 수 카운트
	public static final String SELECT_BID_NOTICE_COUNT = "select count(*) from bid_info";
	
	// user_type으로 제조사, 계약업체 카운트
	public static final String SELECT_MANUFACTURER_COUNT = String.join(" ", //
			"SELECT distinct company_info.* FROM sf_ai_platform.company_info"//
			, "join user_info on (user_info.business_number=company_info.business_number)"//
			, "where user_type=?");

	// edge gateway 전체 개수 조회, 파라미터로 status 붙을 경우 정상/비정상 개수 조회
	public static final String SELECT_EDGEGW_COUNT = "select count(*) from edge_gw_info where 1=1 {where_clause}";
}