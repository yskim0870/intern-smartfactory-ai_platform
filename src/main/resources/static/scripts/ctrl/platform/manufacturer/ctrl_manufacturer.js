platform.controller("manuController", function($scope) {
	
	$scope.company = "";

	// 업태 select box options	
	$scope.conditions = [
		{ "value": "통신", "name": "통신사업" },
		{ "value": "제조", "name": "제조사업" },
		{ "value": "축산", "name": "축산사업" }
	];
	
	// 업종 select box options
	$scope.industryTypes = [
		// 업태를 결정하고 나면 업태에 따른 목록 변화 필요할듯?
		{ "value": "모바일", "name": "모바일 통신" },
		{ "value": "문구", "name": "문구용품 생산" },
		{ "value": "양봉", "name": "양봉" }
	];
	
	// 검색 버튼 클릭 시 검색 조건에 맞게 조회
	$scope.getManuList = function(comapny) {
		selectManuList(comapny);
	}

	// th(= 테이블 칼럼 제목) 클릭시 정렬 기능
	$scope.orderby = {
		"order": "",
		"desc": false
	}
	$scope.sorting = function(order, desc) {
		if (order) {
			$scope.orderby.order = order;
			$scope.orderby.desc = !desc;
		}
		selectManuList();
	}

	// 전체 조회
	let selectManuList = function(company) {

		let params = {
			"name": company.name ? company.name : null,
			"condition": company.condition ? company.condition : null,
			"industryType": company.industryType ? company.industryType : null,
			"orderby": $scope.orderby.order ? $scope.orderby.order : null,
			"desc": $scope.orderby.desc ? $scope.orderby.desc : null,
			"pageNum": $scope.pagination.pageNum,
			"pageItemPerPage": $scope.pagination.pageItemPerPage
		}

		Factory.selectManuList($scope, params, manuResource, $rootScope);
	}
});