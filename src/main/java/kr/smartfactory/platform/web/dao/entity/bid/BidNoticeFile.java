/**
 * 
 */
package kr.smartfactory.platform.web.dao.entity.bid;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @packageName : kr.smartfactory.platform.web.dao.model.bid
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.27
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.27  Younghun Yu  최초 생성
 */
public class BidNoticeFile {

	// 공고 번호
	private Integer bidID;
	
	// 파일 ID
	private String fileID;
	
	// 입찰 공고 파일 타입 0:입찰공고문, 1:샘플데이터
	private Integer fileType;
	
	// 파일명
	private String fileName;
	
	// 파일위치
	private String fileLocation;

	/**
	 * 
	 */
	public BidNoticeFile() {
	}
	
	/**
	 * @param file
	 */
	public BidNoticeFile(String uuid, String fileName, Integer type, String path) {
		this.setFileID(uuid);
		this.setFileName(fileName);
		this.setFileType(type);
		this.setFileLocation(path);
	}

	/**
	 * @param rs
	 */
	public BidNoticeFile(ResultSet rs) {
		
		try {
			this.setBidID(rs.getInt("bid_notice_file.bid_id"));
			this.setFileID(rs.getString("bid_notice_file.file_id"));
			this.setFileType(rs.getInt("bid_notice_file.file_type"));
			this.setFileName(rs.getString("bid_notice_file.file_name"));
			this.setFileLocation(rs.getString("bid_notice_file.file_location"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the bidID
	 */
	public Integer getBidID() {
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
	public Integer getFileType() {
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
	public void setBidID(Integer bidID) {
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
	public void setFileType(Integer fileType) {
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

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidNoticeFile [bidID=");
		builder.append(bidID);
		builder.append(", fileID=");
		builder.append(fileID);
		builder.append(", fileType=");
		builder.append(fileType);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", fileLocation=");
		builder.append(fileLocation);
		builder.append("]");
		return builder.toString();
	}
	
	
}