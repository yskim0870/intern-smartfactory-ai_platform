/**
 * 
 */
package kr.smartfactory.platform.web.dto.bid;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @packageName : kr.smartfactory.platform.web.dto.bid
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
public class BidNoticeFileDTO {

	// 공고 번호
	private int bidID;
	
	// 파일 ID
	private String fileID;
	
	// 입찰 공고문 파일 타입 0:입찰공고문, 1:샘플데이터
	private int fileType;
	
	// 파일명
	private String fileName;
	
	// 파일 위치
	private String fileLocation;
	
	/**
	 * Default Constructor
	 */
	public BidNoticeFileDTO() {
	}

	/**
	 * @param rs
	 */
	public BidNoticeFileDTO(ResultSet rs) {
		try {
			this.setBidID(rs.getInt("bid_id"));
			this.setFileID(rs.getString("file_id"));
			this.setFileType(rs.getInt("file_type"));
			this.setFileName(rs.getString("file_name"));
			this.setFileLocation(rs.getString("file_location"));
		} catch (SQLException e) {
			System.out.printf("Error : %s", e.getMessage());
		}
	}

	/**
	 * @return the bidID
	 */
	public int getBidID() {
		return bidID;
	}

	/**
	 * @return the fileID
	 */
	public String getFileID() {
		return fileID;
	}

	/**
	 * @return the fileType
	 */
	public int getFileType() {
		return fileType;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the fileLocation
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * @param bidID the bidID to set
	 */
	public void setBidID(int bidID) {
		this.bidID = bidID;
	}

	/**
	 * @param fileID the fileID to set
	 */
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @param fileLocation the fileLocation to set
	 */
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}
