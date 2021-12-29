/**
 * 
 */
package kr.smartfactory.platform.web.dao.model.bid;


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
}