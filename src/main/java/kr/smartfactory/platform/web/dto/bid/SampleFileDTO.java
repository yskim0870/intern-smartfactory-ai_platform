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
 * @date : 2022.01.13
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.01.13  Younghun Yu  최초 생성
 */
public class SampleFileDTO {

	List<MultipartFile> smapleFiles;

	/**
	 * @return the fileList
	 */
	public List<MultipartFile> getFileList() {
		return smapleFiles;
	}

	/**
	 * @param fileList the fileList to set
	 */
	public void setFileList(List<MultipartFile> fileList) {
		this.smapleFiles = fileList;
	}
}
