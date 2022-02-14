package kr.smartfactory.platform.web.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import kr.smartfactory.platform.web.dao.entity.User;
import kr.smartfactory.platform.web.dao.entity.UserGrade;
import kr.smartfactory.platform.web.service.IUserService;
import kr.smartfactory.platform.web.service.impl.UserService;

@Configuration
public class UserLoginAuthenticationProvider implements AuthenticationProvider {

	private IUserService userService;

	/**
	 * Desc : Constructor of UserLoginAuthenticationProvider.java class
	 */
	public UserLoginAuthenticationProvider(@Qualifier(UserService.BEAN_QUALIFIER) IUserService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Object principal = authentication.getPrincipal();
		Object password = authentication.getCredentials();

		Collection<GrantedAuthorityDetail> authorities = new ArrayList<GrantedAuthorityDetail>();
		String msg;
		GrantedAuthorityDetail grant;

		// ID / PW가 입력되지 않은 경우
		if (principal == null || password == null || (principal.toString().trim().isEmpty() && password.toString().trim().isEmpty())) {
			msg = "모든 정보를 입력해 주시기 바랍니다.";
			grant = new GrantedAuthorityDetail(UserGrade.getUserGrade(UserGrade.NOT_ENTERED_ID_OR_PASSWORD, null, msg));
			authorities.add(grant);
			return new UsernamePasswordAuthenticationToken(principal, password, authorities);
		}

//		Result<User> resultUser = userService.getUserAndGrade(principal.toString(), password.toString());
//		User user = resultUser.getData();
//
//		// ID / PW에 해당하는 사용자가 없을 경우
//		if (!resultUser.getResult() || resultUser.getData() == null) {
//			msg = "존재하지 않는 사용자 정보";
//			grant = new GrantedAuthorityDetail(UserGrade.getUserGrade(UserGrade.UNKNOWN_USER, null, msg));
//			authorities.add(grant);
//			return new UsernamePasswordAuthenticationToken(principal, password, authorities);
//		}
		
		User user = new User();
		user.setGrade(UserGrade.SUPER_ADMIN);
		user.setName("관리자");
		user.setUserID("admin");
		user.setGradeObj(new UserGrade(UserGrade.MANUFACTURER, "ROLE_ADMIN", "관리자"));

		// 사용자의 권한 및 정보 추가
		// TODO: UserGrade 조회
		grant = new GrantedAuthorityDetail(UserGrade.getUserGrade(user.getGrade(), null, null));
		grant.setUser(user);

		authorities.add(grant);

		return new UsernamePasswordAuthenticationToken(principal, password, authorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return Authentication.class.isAssignableFrom(authentication);
	}

}
