/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 6. 14. 오전 11:35:13
*/

/**
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 6. 14. 오전 11:35:13
*/
package kr.smartfactory.platform.web.controller;

import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.smartfactory.platform.web.dao.model.UserGrade;
import kr.smartfactory.platform.web.security.GrantedAuthorityDetail;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 6. 14. 오전 11:35:13
 *
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String VO_AUTHENTICATION = "authentication";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getLoginView(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView view = new ModelAndView("main");
		handleUrl(view, request, response);

		return view;
	}

	/**
	 * {@link Deprecated} <br>
	 *
	 * <pre>
	 *  
	 * [개정이력]
	 *      날짜      | 작성자 |       내용 
	 * ------------------------------------------
	 * 2021. 12. 8.    yskim   최초작성
	 * </pre>
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param a
	 * @param b
	 * @return
	 */
	@RequestMapping(value = "/{a}/{b}", method = RequestMethod.GET)
	public ModelAndView getUserView(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response//
			, @PathVariable String a//
			, @PathVariable String b//
	) {
		ModelAndView view = new ModelAndView(a + "/" + b);
		return view;
	}

	/**
	 * {@link Deprecated} <br>
	 *
	 * <pre>
	 *  
	 * [개정이력]
	 *      날짜      | 작성자 |       내용 
	 * ------------------------------------------
	 * 2021. 12. 8.    yskim   최초작성
	 * </pre>
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/{path}", method = RequestMethod.GET)
	public ModelAndView getView(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response//
			, @PathVariable String path//
	) {
		ModelAndView view = new ModelAndView(path);
		return view;
	}

	private boolean handleUrl(ModelAndView view, HttpServletRequest request, HttpServletResponse response) {

		boolean intended = true;

		// start - 등록되지 않은 사용자 처리
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			if (logger.isInfoEnabled()) {
				logger.info("(Invalid Security Access) There is no Security.");
			}

			view.setViewName("login");
			view.addObject("message", "환영합니다.");

			return false;
		}
		// end - 등록되지 않은 사용자 처리

		try {

			Iterator<? extends GrantedAuthority> itrAuthority = auth.getAuthorities().iterator();
			GrantedAuthorityDetail authDetail = null;

			while (itrAuthority.hasNext()) {
				authDetail = (GrantedAuthorityDetail) itrAuthority.next();
				UserGrade userGrade = authDetail.getGrade();

				switch (userGrade.getGradeID()) {
					case UserGrade.SUPER_ADMIN:
					case UserGrade.ADMIN:
					case UserGrade.USER:
						String userJSON = new ObjectMapper().writeValueAsString(authDetail.getUser());
						JSONObject obj = new JSONObject(userJSON);
						view.addObject(VO_AUTHENTICATION, obj);
						break;

					case UserGrade.UNKNOWN_USER:
						view.setViewName("login");
						view.addObject("message", "사용자 계정이 존재하지 않습니다.");
						return false;

					case UserGrade.INVALID_UNKNOWN_AND_ERROR:
						view.setViewName("login");
						view.addObject("message", userGrade.getDesc() == null ? "로그인 정보(ID, Password)를 정확히 입력해 주시기 바랍니다." : userGrade.getDesc());
						break;

					case UserGrade.NOT_ENTERED_ID_OR_PASSWORD:
					default:
						view.setViewName("login");
						view.addObject("message", "로그인 정보(ID, Password)를 정확히 입력해 주시기 바랍니다.");
						break;
				}
			}

		} catch (Exception e) {
			logger.warn("(Invalid Authentication)", e);
			view.setViewName("login");
			view.addObject("message", "로그인 도중 오류가 발생하였습니다.\n관리자에게 문의하시기 바랍니다.");
			intended = false;
		}

		return intended;
	}

}
