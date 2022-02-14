/**
 * 
 */
package kr.smartfactory.platform.web.dto.bid;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

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

	private MultipartFile[] bidFiles;
	
	/**
	 * 
	 */
	public BidNoticeFileDTO() {

	}

	/**
	 * @return the bidFiles
	 */
	public MultipartFile[] getBidFiles() {
		return bidFiles;
	}

	/**
	 * @param bidFiles the bidFiles to set
	 */
	public void setBidFiles(MultipartFile[] bidFiles) {
		this.bidFiles = bidFiles;
	}
}