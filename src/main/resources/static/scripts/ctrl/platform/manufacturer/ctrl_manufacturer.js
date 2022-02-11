platform.controller("manuController", function($scope, Factory, $rootScope) {

	// 업태 select box options	
	Factory.companyResource.getCondition(
		{ "param1": "condition" },
		null,
		function(res) {
			let list = res.data;
			$scope.conditions = [];

			list.forEach(function(companyInfo) {
				if (companyInfo.condition != null) {
					$scope.conditions.push({ "name": companyInfo.condition, "value": companyInfo.condition });
				}
			});
		},
		function(res) {
			alert(res.data);
		}
	);

	// 업종 select box options
	Factory.companyResource.getIndustryType(
		{ "param1": "industry-type" },
		null,
		function(res) {
			let list = res.data;
			$scope.industryTypes = [];

			list.forEach(function(companyInfo) {
				if (companyInfo.industryType != null) {
					$scope.industryTypes.push({ "name": companyInfo.industryType, "value": companyInfo.industryType });
				}
			});
		},
		function(res) {
			alert(res.data);
		}
	);

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
		selectCompanyList();
	}

	// 검색 버튼 클릭 시 검색 조건에 맞게 조회
	$scope.getCompanyList = function() {
		selectCompanyList();
		$scope.company.name = null;
		$scope.industryType = null;
		$scope.condition = null;
	}

	// th(= 테이블 칼럼 제목) 클릭시 정렬 기능
	$scope.orderby = {
		"order": "",
		"desc": false
	}
	$scope.sort = {
		"name": false,
		"businessNumber": false,
		"ceoName": false,
		"telNumber": false,
		"address": false
	}
	$scope.sorting = function(order, sort) {
		if (order) {
			$scope.orderby.order = order;
			$scope.orderby.desc = !$scope.orderby.desc;
			$scope.sort = [false, false, false, false, false];

			if (sort == 'name') {
				$scope.sort[0] = true;
			}
			else if (sort == 'businessNumber') {
				$scope.sort[1] = true;
			}
			else if (sort == 'ceoName') {
				$scope.sort[2] = true;
			}
			else if (sort == 'telNumber') {
				$scope.sort[3] = true;
			}
			else if (sort == 'address') {
				$scope.sort[4] = true;
			}
		}
		selectCompanyList();
	}

	// 전체 조회
	let selectCompanyList = function() {

		let companyResource = Factory.companyResource;
		let userType = $rootScope.authentication.userGrade;

		$scope.company = "";
		let params = {
			// 제조사만 조회하기 위한 user type (path variable)
			"userType": userType == 0 ? 1 : null,
			"name": $scope.company.name ? $scope.company.name : null,
			"condition": $scope.condition ? $scope.condition : null,
			"industryType": $scope.industryType ? $scope.industryType : null,
			"orderby": $scope.orderby.order ? $scope.orderby.order : null,
			"desc": $scope.orderby.desc,
			"pageNum": $scope.pagination.pageNum,
			"pageItemPerPage": $scope.pagination.pageItemPerPage
		}

		Factory.getCompanyList($scope, params, companyResource, $rootScope, Factory.dateHandling);
	}
	selectCompanyList();
});