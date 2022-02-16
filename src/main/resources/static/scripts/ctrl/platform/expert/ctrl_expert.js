platform.controller("ExpertController", function($scope, Factory, $rootScope) {

	$scope.company = "";

	// pagination 아이템 출력 갯수
	$scope.pageOptions = [
		{ "value": "15", "name": "15개씩" },
		{ "value": "30", "name": "30개씩" },
	];

	$scope.pagination = {
		pageNum: 1,
		pageItemPerPage: $scope.pageOptions[0].value,
		maxSize: 10,
		totalCount: 0,
	}

	// th(= 테이블 칼럼 제목) 클릭시 정렬 기능
	$scope.orderby = {
		"order": null,
		"desc": false
	}
	$scope.sorting = function(order, sort) {
		if (order) {
			$scope.orderby.order = order;
			$scope.orderby.desc = !$scope.orderby.desc;
			$scope.sort = [false, false, false, false, false, false];

			if (sort == 'expertManager') {
				$scope.sort[0] = true;
			}
			else if (sort == 'companyName') {
				$scope.sort[1] = true;
			}
			else if (sort == 'telNumber') {
				$scope.sort[2] = true;
			}
			else if (sort == 'email') {
				$scope.sort[3] = true;
			}
			else if (sort == 'businessNumber') {
				$scope.sort[4] = true;
			}
			else if (sort == 'status') {
				$scope.sort[5] = true;
			}
		}

		selectCompanyList();
	}
	
	// 검색 버튼 클릭 시 검색 조건에 맞게 조회
	$scope.getCompanyList = function() {
		selectCompanyList();
		$scope.company.name = null;
		$scope.company.status = null;
	}

	// 전체 조회
	let selectCompanyList = function() {

		let companyResource = Factory.companyResource;
		let dateHandling = Factory.dateHandling;
		let bidResource = Factory.bidResource;
		let userType = $rootScope.authentication.userGrade;

		let params = {
			// 계약업체만 조회하기 위한 user type (path variable)
			"userType": userType == 0 ? 2 : null,
			"name": $scope.company.name ? $scope.company.name : null,
			"status": $scope.company.status ? $scope.company.status : null,
			"orderby": $scope.orderby.order ? $scope.orderby.order : null,
			"desc": $scope.orderby.desc,
			"pageNum": $scope.pagination.pageNum,
			"pageItemPerPage": $scope.pagination.pageItemPerPage
		}

		Factory.getCompanyList($scope, params, companyResource, $rootScope, dateHandling, bidResource);
	}
	selectCompanyList();
});