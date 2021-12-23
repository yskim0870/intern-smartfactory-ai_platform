/**
 * @ProgramName : ctrl_content_menu_container.js
 * 
 * Description: This is a xxxxxxxxxxxxxxxxxxxxxxxxxxxxx, and is executed continuously and interrupted Only to perform in case of reset or failure
 * detection.
 * @Package : src/main/webapp/scripts/ctrl/beeai
 * @Project : ksb.beeai.webtoolkit
 * @Type : ServiceContentCtrl
 * 
 * @Revision_history: Date : 2017. 9. 14., Author : yskim, Version : 1.0
 * 
 * COPYRIGHT(c) 2016, KSB(Knowledge-converged Super Brain) Convergence Research Department, ETRI.
 * 
 * Opensource License: Copyright (C) 2016 The KSB Open Source Project Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

ServiceContentCtrl = function($scope, $http, $route, $routeParams, $location, $rootScope, content) {

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	// /// start - 디버깅 용도

	if ($rootScope.logger.isDebuggable()) {
		console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		console.log("[Controller - Here comes 'Content Controller'.");
		console.debug("content: ", content);
		console.debug("content: ", content.content);
	}
	// /// end - 디버깅 용도
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////

	$scope.templateUrlStyle = new Style();

	// 메뉴 선택 CSS 변경
	$scope.updateCurrentContent(content);
	
	// 메뉴 컨텐츠(서브메뉴) 선택
	var contentID = $routeParams["content_id"];

	if (contentID == undefined || contentID == null) {
		content.updateCurrent(0);
	} else {
		content.updateCurrent(contentID);
	}

	if (content.current == null) {

		if ($rootScope.logger.isDebuggable()) {
			console.warn("(잘못된 URL 요청이 전달되었습니다) url: /" + content.content + "/" + contentID);
		}

		// 잘못된 URL 값을 $rootScope를 이용해서 공유
		$rootScope.invalidUrl = "/" + content.content + "/" + contentID;

		$location.path("/error_handle/invalid_url");
	} else {
		$scope.content = content;
	}

	if ($rootScope.logger.isDebuggable()) {
		console.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	// sub menu 선택함수 호출
	$scope.selectMenu($scope.routeContents, $scope.content, $scope.content.current);
	
};