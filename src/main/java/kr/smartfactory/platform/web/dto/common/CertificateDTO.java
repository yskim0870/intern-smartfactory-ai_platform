/**
 * 
 */
package kr.smartfactory.platform.web.dto.common;

/**
 * @packageName : kr.smartfactory.platform.web.dto.common
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.02.09
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.02.09  Younghun Yu  최초 생성
 */
public class CertificateDTO {
		// pk 유저 아이디
		String userID;
		
		// pk 자격증 번호
		String certificateID;
		
		// 자격증 종류(이름)
		String certificateType;
		
		// 취득 날짜
		Long acquisitionDate;

		/**
		 * @return the userID
		 */
		public String getUserID() {
			return userID;
		}

		/**
		 * @param userID the userID to set
		 */
		public void setUserID(String userID) {
			this.userID = userID;
		}

		/**
		 * @return the certificateID
		 */
		public String getCertificateID() {
			return certificateID;
		}

		/**
		 * @param certificateID the certificateID to set
		 */
		public void setCertificateID(String certificateID) {
			this.certificateID = certificateID;
		}

		/**
		 * @return the certificateType
		 */
		public String getCertificateType() {
			return certificateType;
		}

		/**
		 * @param certificateType the certificateType to set
		 */
		public void setCertificateType(String certificateType) {
			this.certificateType = certificateType;
		}

		/**
		 * @return the acquisitionDate
		 */
		public Long getAcquisitionDate() {
			return acquisitionDate;
		}

		/**
		 * @param acquisitionDate the acquisitionDate to set
		 */
		public void setAcquisitionDate(Long acquisitionDate) {
			this.acquisitionDate = acquisitionDate;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Certificate [userID=");
			builder.append(userID);
			builder.append(", certificateID=");
			builder.append(certificateID);
			builder.append(", certificateType=");
			builder.append(certificateType);
			builder.append(", acquisitionDate=");
			builder.append(acquisitionDate);
			builder.append("]");
			return builder.toString();
		}
}
