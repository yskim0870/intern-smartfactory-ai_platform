/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 6. 23. 오후 11:15:21
 */
RouteContent = function(content) {
	this.content = content.root;
	this.display = content.display;
	this.url = content.root;
	this.role = content.role;
	this.menus = content.menus;
	this.selected = ""; // 현재 선택된 RooteContentdp 에 사용되는
};

RouteContent.prototype.toString = function() {
	return JSON.stringify(this, null, "\t");
};

RouteContent.prototype.getCss = function() {
	return this.selected ? "rollover" : "";
}

RouteContent.prototype.getUrl = function(standalone) {
	return standalone ? this.url : "#/" + this.url;
};

Authentication = function(auth) {
	this.userID = auth.userID;
	this.userName = auth.name;
	this.userGrade = auth.grade;
	this.userGradeObj = auth.gradeObj;
};

var ngFilter = new NgFilter();
platform.filter("startFrom", ngFilter.startFrom);

var ngFactory = new NgFactory();
platform.factory("ColorPicker", ngFactory.colorPicker);

var ngDirective = new NgDirective();
platform.directive("ngRightClick", ngDirective.ngRightClick);
platform.directive("ngConfirmClick", ngDirective.ngConfirmClick);
platform.directive("dlEnterKey", ngDirective.dlEnterKey);

platform.controller("PlatformCtrl", function($scope, $http, $rootScope, $uibModal, $log) {

	$rootScope.logger = new NgLogger("debug");

	if ($rootScope.logger.isDebuggable()) {
		console.log("[Controller] Here comes a PlatformCtrl at", new Date());
	}

	$scope.ctx = ctx;
	$rootScope.authentication = new Authentication(AUTHENTICATION);

	if ($rootScope.logger.isDebuggable()) {
		console.log("[Authentication] Welcome", $rootScope.authentication);
	}

	$scope.routeContents = [//
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.DASHBOARD), $rootScope.authentication.userGrade),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.BID_MGMT), $rootScope.authentication.userGrade),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.MANUFACTURER_MGMT), $rootScope.authentication.userGrade),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.DOMAIN_IT_EXPERT_MGMT), $rootScope.authentication.userGrade),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.DATA_MGMT), $rootScope.authentication.userGrade),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.EDGE_GW_MGMT), $rootScope.authentication.userGrade),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.BILLING_MGMT), $rootScope.authentication.userGrade),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.ALARM_MGMT), $rootScope.authentication.userGrade), 
	];

	// 메뉴 노출
	$scope.isAllowedContent = function(content) {
		if ($rootScope.logger.isTraceable()) {
			console.log("[Is Allowed Content", content.content);
		}

		return content.role.contains($rootScope.authentication.userGrade);
	}

	// 서브 메뉴 노출
	$scope.isAllowedSubmenu = function(menu) {
		if ($rootScope.logger.isTraceable()) {
			console.log("[Is Allowed Submenu", menu.key);
		}

		return menu.role.contains($scope.auth.userGrade);
	}

	// 메뉴 갱신
	$scope.updateCurrentContent = function(content) {
		var url = "#/" + content.content;
//		var url = "#!/" + content.content;
		var _content_ = null;

		for (var i = 0; i < $scope.routeContents.length; i++) {
			_content_ = $scope.routeContents[i];
			_content_.selected = url == _content_.getUrl();
		}

		$scope.content = content;
		
		if ($rootScope.logger.isDebuggable()) {
			console.log("(Current Content)", $scope.content);
		}
		
	};
	
	/** sub menu 선택 함수 */
	$scope.selectMenu = function(contents, currentContent, currentMenu){
		contents.forEach(function(content){
			content.menus.values().forEach(function(menu){
				menu.selected = false;
				if(currentMenu != null && currentContent.id == content.id && menu.key == currentMenu.key){
					menu.selected = true;
				}
			});
		})
	}
	
});

// 상단 메뉴 Route 설정
platform.config(function($routeProvider, $locationProvider) {

	// temp user data
	var user = new Authentication(AUTHENTICATION);
	var routeWhen = null;

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.DASHBOARD, user.userGrade);
	$routeProvider.when("/", routeWhen);
	$routeProvider.when("/dashboard", routeWhen);
	$routeProvider.when("/dashboard/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.BID_MGMT, user.userGrade);
	$routeProvider.when("/bid", routeWhen);
	$routeProvider.when("/bid/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.MANUFACTURER_MGMT, user.userGrade);
	$routeProvider.when("/manufacturer", routeWhen);
	$routeProvider.when("/manufacturer/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.DOMAIN_IT_EXPERT_MGMT, user.userGrade);
	$routeProvider.when("/expert", routeWhen);
	$routeProvider.when("/expert/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.DATA_MGMT, user.userGrade);
	$routeProvider.when("/data", routeWhen);
	$routeProvider.when("/data/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.EDGE_GW_MGMT, user.userGrade);
	$routeProvider.when("/edge-gw", routeWhen);
	$routeProvider.when("/edge-gw/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.BILLING_MGMT, user.userGrade);
	$routeProvider.when("/billing", routeWhen);
	$routeProvider.when("/billing/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.ALARM_MGMT, user.userGrade);
	$routeProvider.when("/alarm", routeWhen);
	$routeProvider.when("/alarm/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.MY_PAGE, user.userGrade);
	$routeProvider.when("/my-page", routeWhen);
	$routeProvider.when("/my-page/:content_id", routeWhen);

	// ////////////////////////////////////////////////////////////////////////////
	// start - Route for 'Unknown URL Request' : 2016. 2. 24., Park_Jun_Hong_(fafanmama_at_naver_com)
	routeWhen = ContentRouteConfig.loadInvalidUrlRoute();
	$routeProvider.when("/error_handle", routeWhen);
	$routeProvider.when("/error_handle/:content_id", routeWhen);
	$routeProvider.when("/error_handle/e/:content_id", routeWhen);
	// end - Route for 'Unknown URL Request' : 2016. 2. 24.
	// ////////////////////////////////////////////////////////////////////////////

	$routeProvider.otherwise({
		redirectTo : "/error_handle"
	});

	// $locationProvider.html5Mode({
	// enabled: true,
	// requireBase: false
	// });

});