platform.controller("manuController", function($scope, Factory, $rootScope) {
	
	$scope.company = "";

	// 업태 select box options	
	$scope.conditions = [
		{ "value": "통신", "name": "통신사업" },
		{ "value": "제조", "name": "제조사업" },
		{ "value": "축산", "name": "축산사업" }
	];

	// 업종 select box options
	$scope.industryTypes = [
		{ "value": "모바일", "name": "모바일 통신" },
		{ "value": "문구", "name": "문구용품 생산" },
		{ "value": "양봉", "name": "양봉" }
	];

	// pagination 아이템 출력 갯수
	$scope.pageOptions = [
		{ "value": "15", "name": "15개씩" },
		{ "value": "30", "name": "30개씩" },
	];

	// 페이지네이션 기본 설정
	$scope.pagination = {
		pageNum: 1,
		pageItemPerPage: $scope.pageOptions[0].value,
		maxSize: 10,
		totalCount: 0,
	}

	// 15개씩, 30개씩 해당 갯수에 맞게 조회
	$scope.changePageOption = function() {
		selectBidList();
	}

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
	let selectManuList = function() {
		
		// 제조사만 조회하기 위한 user type (path variable)
		let companyResource = Factory.companyResource;

		let params = {
			"userType": 1,
			"name": $scope.company.name ? $scope.company.name : null,
			"condition": $scope.company.condition ? $scope.company.condition : null,
			"industryType": $scope.company.industryType ? $scope.company.industryType : null,
			"orderby": $scope.orderby.order ? $scope.orderby.order : null,
			"desc": $scope.orderby.desc ? $scope.orderby.desc : null,
			"pageNum": $scope.pagination.pageNum,
			"pageItemPerPage": $scope.pagination.pageItemPerPage
		}

		Factory.getCompanyList($scope, params, companyResource, $rootScope, Factory.dateHandling);
	}
	selectManuList();
});