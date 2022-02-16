/**
 * @ProgramName : SecurityUtil.java
 *
 * Description: This is a SecurityUtil, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : ksb.csle.ui.utils
 * @Project : ksb.csle.ui
 * @Type :  SecurityUtil
 *
 * @Revision_history:
 *   Date : 2017. 9. 14..,  Author : Park_Jun_Hong_(fafanmama_at_naver_com),  Version : 1.0
 * 
 * COPYRIGHT(c) 2016, KSB(Knowledge-converged Super Brain) Convergence Research Department, ETRI.
 * 
 * Opensource License:
 * Copyright (C) 2016 The KSB Open Source Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.smartfactory.platform.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @subject : 로그인 보안 유틸리티
 * 
 * 
 * 
 * @revision_history : Park_Jun_Hong_(fafanmama_at_naver_com), 2017. 9. 14., 1.0
 */
public class SecurityUtil {

	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(SecurityUtil.class);

	/**
	 * Spring Security 인증 정보를 제거한다.
	 * 
	 * <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2017. 9. 14.		박준홍			최초 작성
	 * </pre>
	 *
	 *
	 * @author: Park_Jun_Hong_(fafanmama_at_naver_com)
	 * @since 2017. 9. 14.
	 */
	public static void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * Http Session 을 제거한다.
	 * 
	 * <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2017. 9. 14.		박준홍			최초 작성
	 * </pre>
	 *
	 * @param request
	 *
	 * @author: Park_Jun_Hong_(fafanmama_at_naver_com)
	 * @since 2017. 9. 14.
	 */
	public static void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 현재 로그인한 유저의 ID를 반환한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @return
	 */
	public static String getCurrentUserID() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return auth != null ? (String) auth.getPrincipal() : null;
	}

}
