/**
 * 
 */
package kr.smartfactory.platform.web.dto.common;

/**
 * @packageName : kr.smartfactory.platform.web.dto
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
public class UserGradeDTO {

	// 권한 ID - 0: 관리자, 1: 제조사, 2: 도메인IT전문업체
	private int id; 
	
	// 권한 이름
	private String name;
	
	// 권한 설명
	private String desc;
	
	/**
	 * Default Constructor
	 */
	public UserGradeDTO() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}