package kr.smartfactory.platform.web.security;

import org.springframework.security.core.GrantedAuthority;

import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dao.entity.UserGrade;

public class GrantedAuthorityDetail implements GrantedAuthority  {

	private static final long serialVersionUID = 1L;

	private final UserGrade grade;

	private User user;

	public GrantedAuthorityDetail(UserGrade grade) {
		this.grade = grade;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return this.grade.getName();
	}

	/**
	 * @return the grade
	 */
	public UserGrade getGrade() {
		return grade;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}