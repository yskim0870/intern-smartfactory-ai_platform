// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'Content Menu' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
/**
 * 
 * @param key
 *            {string}: menu key
 * @param templateUrl
 *            {string}: template file's path
 * 
 * @param display
 *            {string}: UI 에 보여질 내용.
 * @param desc
 *            {string}: 메뉴 설명
 * @param role
 *            {array of number}: 롤 등급 배열
 * @param standalone
 *            {boolean}: 메뉴가 별도 URL로 처리되는지 여부
 * @param shadow
 *            {boolean}: 서브메뉴로 노출되지 않는지 여부
 * 
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 5.
 */
ContentMenu = function(key, templateUrl, display, desc, role, icon, standalone, shadow, isShow) {
	this.key = key;
	this.templateUrl = templateUrl;
	this.display = display;
	this.desc = desc;
	this.role = role;

	// ng-view 영역이 아닌 별도의 창으로 열리는 메뉴
	this.standalone = Object.available(standalone) ? standalone : false;

	this.icon = icon;
	
	// 실제 하위메뉴가 아닌 경우 (메뉴 구조에 맞추기 위함)
	this.shadow = Object.available(shadow) ? shadow : false;
	this.selected = false;
	
	// registry 메뉴에서만 사용, 
	// registry 메뉴 하위에 좌측 메뉴로 표시할 지에 대한 설정
	this.isShow = Object.available(isShow) ? isShow : false;
};

ContentMenu.prototype.hasDesc = function() {
	return this.desc != undefined && this.desc != null && !this.desc.isEmptyString();
}

ContentMenu.prototype.toString = function() {
	return JSON.stringify(this, null, "\t");
}
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'Content Menu' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'Content Menu Container' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
Content = function(content, userGrade) {
	this.id = Object.getPropertyValue(content, "id", null); // Content ID
	this.content = Object.getPropertyValue(content, "content", null); // Content 고육 식별값
	this.display = Object.getPropertyValue(content, "display", null); // UI에 보여질 Content 정보
	this.role = Object.getPropertyValue(content, "role", null); // 현재 콘텐츠 Role ID 배열
	this.menus = new FIFOMap(); // ContentMenu

	if (Object.available(content) && Object.available(content.menus)) {
		// 사용자 등급에 따라 하위 메뉴 선택적 추가
		// 이유: 동일한 페이지더라도 사용자 등급에 따라서 다르게 구성해야 하는 경우에 개발자들의 편의를 위해서 적용
		if (Object.available(userGrade)) {
			content.menus.forEach(function(elem, index, array) {
				if (elem.role.contains(userGrade)) {
					this.menus.put(elem.key, elem);
				}
			}, this);
		} else {
			content.menus.forEach(function(elem, index, array) {
				this.menus.put(elem.key, elem);
			}, this);
		}
	}

	this.current = null; // 현재 선택된 ContentMenu
	this.icon = Object.getPropertyValue(content, "icon", null); // Menu Icon  
	this.selected = false; // 선택됐는지 여부
	this.standalone = Object.getPropertyValue(content, "standalone", null); // 
};

/**
 * 메뉴를 생성한다.
 * 
 * @param content
 *            {object} 메뉴 콘텐츠 Anonymous object
 * 
 * @param userGrade
 *            {number} 사용자 등급
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 5.
 */
Content.prototype.loadContents = function(content, userGrade) {
	this.id = Object.getPropertyValue(content, "id", null); // Content ID
	this.content = Object.getPropertyValue(content, "content", null); // Content 고육 식별값
	this.display = Object.getPropertyValue(content, "display", null); // UI에 보여질 Content 정보
	this.role = Object.getPropertyValue(content, "role", null); // 현재 콘텐츠 Role ID 배열
	this.menus = new FIFOMap(); // ContentMenu

	if (Object.available(content) && Object.available(content.menus)) {
		// 사용자 등급에 따라 하위 메뉴 선택적 추가
		// 이유: 동일한 페이지더라도 사용자 등급에 따라서 다르게 구성해야 하는 경우에 개발자들의 편의를 위해서 적용
		if (Object.available(userGrade)) {
			content.menus.forEach(function(elem, index, array) {
				if (elem.role.contains(userGrade)) {
					this.menus.put(elem.key, elem);
				}
			}, this);
		} else {
			content.menus.forEach(function(elem, index, array) {
				this.menus.put(elem.key, elem);
			}, this);
		}
	}

	this.current = null; // 현재 선택된 ContentMenu
	
	this.selected = false;
};

/**
 * 메뉴의 개수를 반환한다.
 * 
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 5.
 */
Content.prototype.count = function() {
	return this.menus.size();
};

/**
 * 메뉴의 개수를 반환한다.
 * 
 * @returns {Number}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 14.
 */
Content.prototype.countOfMenus = function() {

	var count = 0;

	if (Object.available(this.menus)) {

		this.menus.values().forEach(function(elem, idx) {
			count += (elem.shadow ? 0 : 1);
		});
	}

	return count;
}

Content.prototype.getContentName = function(upperCase) {
	return upperCase ? this.content.toUpperCaseCharAt(0) : this.content;
};

Content.prototype.getCss = function() {

	var hasSub = this.countOfMenus() > 0 ? "has-sub " : "";

	return this.selected ? hasSub + "active" : hasSub;
}

Content.prototype.getUrl = function() {
//	return "#!/" + this.content;
	return "#/" + this.content;
};

Content.prototype.toString = function() {
	return JSON.stringify(this, null, "\t");
};

Content.prototype.updateCurrent = function(key) {
	if (this.menus.size() < 1) {
		return;
	}

	if (key == undefined || key == null) {
		this.current = this.menus.values()[0];
	} else if (typeof key == "number" && this.count() > key) {
		this.current = this.menus.values()[key];
	} else if (typeof key == "string") {
		this.current = this.menus.get(key);
	}

	// 현재 선택된 메뉴에 대해서 class="" 적용
	if (this.current != undefined && this.current != null) {
		this.current.selected = "active";
	}
	
};

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'Content Menu Container' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'Object for $routeProvider.when(url, route) function' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
/**
 * 
 * @param templateUrl
 * @param controller
 *            {string}: a name of a controller
 * @param controllerAs
 *            {string}: alias of controller
 * @param content
 *            {object}: see {@link ContentRouteConfig#getContents()}
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 5.
 */
RouteWhen = function(templateUrl, controller, controllerAs, content) {
	this.templateUrl = templateUrl;
	this.controller = controller;
	this.controllerAs = controllerAs;
	this.resolve = {
		content : content,
	};
	this.redirectTo = null;
};
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'Object for $routeProvider.when() function' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// 왼쪽 메뉴 구성 템플릿
var tplContent = ctx + "/static/templates/platform/tpl_content_menu_container.html";

ContentRouteConfig = function() {

};

ContentRouteConfig.INVALID_CONTENT = -0x01;

ContentRouteConfig.DASHBOARD = 0x00;
ContentRouteConfig.BID_MGMT = 0x01;
ContentRouteConfig.MANUFACTURER_MGMT = 0x02;
ContentRouteConfig.DOMAIN_IT_EXPERT_MGMT = 0x03;
ContentRouteConfig.DATA_MGMT = 0x04;
ContentRouteConfig.EDGE_GW_MGMT = 0x05;
ContentRouteConfig.BILLING_MGMT = 0x06;
ContentRouteConfig.ALARM_MGMT = 0x07;

ContentRouteConfig.ROLE_SUPER_ADMIN = 0x00;
ContentRouteConfig.ROLE_ADMIN = 0x01;
ContentRouteConfig.ROLE_USER = 0x02;

/**
 * 
 * @param id
 * @returns
 * 
 * <pre>
 * 	{
 * 		content: content url
 * 		, display: display text
 * 		, role: user role
 * 		, menus: 'ContentMenu' list
 * 	}
 * </pre>
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 5
 */
ContentRouteConfig.getContents = function(id) {
	switch (id) {
	case ContentRouteConfig.INVALID_CONTENT: // 
		return {
			id : ContentRouteConfig.INVALID_CONTENT,
			content : "error_handle",
			display : "잘못된 URL 처리",
			role : [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
				new ContentMenu("invalid_url", ctx + "/static/templates/invalid/tpl_content_invalid_url.html"//
				, "Warning", "잘못된 URL 요청입니다."//
				, [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
				], "glyphicon-alert", false)
			//
			]
		};
		
	// 대쉬보드
	case ContentRouteConfig.DASHBOARD:
		return {
			id : ContentRouteConfig.DASHBOARD,
			content : "dashboard",
			display : "대시보드",
			role : [
				ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
			         new ContentMenu("dashboard", ctx + "/static/templates/platform/dashboard/tpl_content_dashboard.html"//
					, "대시보드", ""//
					, [
						ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER 
					], "ico_board", false, true, true) //
			],
			icon : "ico_board"
		};
		
		
	// 입찰공고
	case ContentRouteConfig.BID_MGMT:
		return {
			id : ContentRouteConfig.BID_MGMT,
			content : "bid",
			display : "입찰공고",
			role : [
				ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
				],
				menus : [
					new ContentMenu("list", ctx + "/static/templates/platform/bid/tpl_content_bid_list.html"//
					, "입찰 조회", ""//
					, [
						ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
					], "fa fa-tasks", false, false, true),//
					new ContentMenu("mgmt", ctx + "/static/templates/platform/bid/tpl_content_bid_mgmt.html"//
					, "입찰관리", ""//
					, [
						ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
					], "fa fa-tasks", false, false, true),//
			],
			icon : "ico_bid",
		};
		
	
	// 제조사 관리
	case ContentRouteConfig.MANUFACTURER_MGMT:
		return {
		id : ContentRouteConfig.MANUFACTURER_MGMT,
		content : "manufacturer",
		display : "제조사 관리",
		role : [
			ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
				new ContentMenu("mgmt", ctx + "/static/templates/platform/manufacturer/tpl_content_manufacturer_mgmt.html"//
				, "제조사 관리", ""//
				, [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
				], "ico_fac", false, true, false), //
			],
			icon : "ico_fac"
		};
	
	// 도메인 IT 전문가 관리
	case ContentRouteConfig.DOMAIN_IT_EXPERT_MGMT:
		return {
		id : ContentRouteConfig.DOMAIN_IT_EXPERT_MGMT,
		content : "expert",
		display : "도메인 IT 전문가 관리",
		role : [
			ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
				new ContentMenu("mgmt", ctx + "/static/templates/platform/expert/tpl_content_expert_mgmt.html"//
				, "도메인 IT 전문가 관리", ""//
				, [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
				], "fa fa-sitemap", false, true, false), //
			],
			icon : "ico_it"
		};
	
	// 데이터 관리
	case ContentRouteConfig.DATA_MGMT:
		return {
		id : ContentRouteConfig.DATA_MGMT,
		content : "data",
		display : "데이터 관리",
		role : [
			ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
	        ],
	        menus : [
	        	new ContentMenu("mgmt", ctx + "/static/templates/platform/data/tpl_content_data_mgmt.html"//
        		, "데이터 관리", ""//
        		, [
        			 ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
        		], "ico_data", false, true, true) //
	         ],
	         icon : "ico_data"
		};
		
	// EDGE_GW 관리
	case ContentRouteConfig.EDGE_GW_MGMT:
		return {
		id : ContentRouteConfig.EDGE_GW_MGMT,
		content : "edge-gw",
		display : "Edge Gateway 관리",
		role : [
			ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
				new ContentMenu("mgmt", ctx + "/static/templates/platform/edgegw/tpl_content_edgegw_mgmt.html"//
				, "Edge Gateway 관리", ""//
				, [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
				], "ico_gw", false, true, true) //
			],
			icon : "ico_gw"
		};

	// 과금관리
	case ContentRouteConfig.BILLING_MGMT:
		return {
		id : ContentRouteConfig.BILLING_MGMT,
		content : "billing",
		display : "과금 관리",
		role : [
			ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
				new ContentMenu("mgmt", ctx + "/static/templates/platform/billing/tpl_content_billing_mgmt.html"//
				, "과금 관리", ""//
				, [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
				], "ico_pay", false, true, true) //
			],
			icon : "ico_pay"
		};

	// 알람 관리
	case ContentRouteConfig.ALARM_MGMT:
		return {
		id : ContentRouteConfig.ALARM_MGMT,
		content : "alarm",
		display : "알람 메시지 관리",
		role : [
			ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
				new ContentMenu("mgmt", ctx + "/static/templates/platform/alarm/tpl_content_alarm_mgmt.html"//
				, "알람 메시지 관리", ""//
				, [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
				], "ico_set", false, true, true) //
			],
			icon : "ico_set"
		};		
		
	// 마이 페이지
	case ContentRouteConfig.MY_PAGE:
		return {
		id : ContentRouteConfig.MY_PAGE,
		content : "my-page",
		display : "My Page",
		role : [
			ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
			],
			menus : [
				new ContentMenu("my-page", ctx + "/static/templates/platform/management/tpl_content_my_page.html"//
				, "My Page", ""//
				, [
					ContentRouteConfig.ROLE_SUPER_ADMIN, ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER
					], "fa fa-user", false, true, true) //
				],
				icon : "fa fa-user"
		};
	
	};
};

// ////////////////////////////////////////////////////////////////////////////
// ////////////////////////////////////////////////////////////////////////////
// /// start - IPASMS 서비스

/**
 * 메뉴별 Route 설정을 반환한다.
 * 
 * @param contentId
 *            {number} 메뉴 콘텐트 ID
 * @param userGrade
 *            {number} 사용자 등급
 */
ContentRouteConfig.loadRoute = function(contentId, userGrade) {
	var menuconfig = function() {
		return new Content(ContentRouteConfig.getContents(contentId), userGrade);

	};

	return new RouteWhen(tplContent, ServiceContentCtrl, "menuContainer", menuconfig);
}

// /// end - IPASMS 서비스
// ////////////////////////////////////////////////////////////////////////////
// ////////////////////////////////////////////////////////////////////////////

/**
 * 잘못된 URL 요청 처리를 위한 설정을 반환한다.
 * 
 * @returns {RouteWhen}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 5.
 */
ContentRouteConfig.loadInvalidUrlRoute = function() {
	var menuconfig = function() {
		var cmc = new Content();
		cmc.loadContents(ContentRouteConfig.getContents(ContentRouteConfig.INVALID_CONTENT));

		return cmc;
	};

	return new RouteWhen(tplContent, ServiceContentCtrl, "menuContainer", menuconfig);
};
