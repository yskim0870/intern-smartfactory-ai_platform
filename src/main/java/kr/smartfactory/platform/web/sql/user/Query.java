/**
 * 
 */
package kr.smartfactory.platform.web.sql.user;

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
public class Query {

	/**
	 * 회사정보
	 * -  사업자등록번호, 회사명, 회사주소, 업태, 업종, 대표번호, 대표자이름
	 */
	public final static String INSERT_COMPANY_INFO_QUERY = ""
			+ "insert into "
			+ "company_info(`business_number`, `name`, `address`, `condition`, `industry_type`, `tel_number`, `fax_number`, `site_url`, `ceo_name`) "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	/** 
	 * 사용자정보
	 * - 성명, 연락처, 메일, 부서, 직급
	 */
	public final static String INSERT_USER_INFO_QUERY = ""
			+ "insert into "
			+ "user_info(`id`, `password`, `user_type`, `business_number`, `name`, `tel_number`, `email`, `department`, `rank`, `reg_date`) "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * 
	 */
	public final static String SELECT_USER_LIST_QUERY = "select * from user";
    
	/**
	 * 
	 */
	public final static String SELECT_USER_QUERY = "select * from user where id=?";
    
	/**
	 * 
	 */
	public final static String UPDATE_USER_QUERY = "update user set pwd=?, name=?, age=?, gender=? where id=?";
}