platform.controller("BidCtrl", function($scope, Factory, $rootScope) {

	let bidResource = Factory.bidResource;
	let dateHandling = Factory.dateHandling;
	
	// 대시보드에서 입찰공고 목록을 조회하기 위해 별도로 사용하는 변수
	$scope.dash = 0;

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

	// 15개씩, 30개씩 해당 갯수에 맞게 조회
	$scope.changePageOption = function() {
		selectBidList();
	}

	// ------------------ select bid list ------------------

	// 검색 버튼 클릭 시 검색 조건에 맞게 조회
	$scope.showBidView = function() {
		selectBidList();
		$scope.id = null;
		$scope.bidStartDate = null;
		$scope.bidEndDate = null;
		$scope.bidName = null;
		$scope.manufacturerName = null;
		$scope.status = null;
	}

	// th(= 테이블 칼럼 제목) 클릭시 정렬 기능
	$scope.orderby = {
		"order": "",
		"desc": false
	}
	$scope.sorting = function(order, sort) {
		if (order) {
			$scope.orderby.order = order;
			$scope.orderby.desc = !$scope.orderby.desc;
			$scope.sort = [false, false, false, false, false];
			
			if(sort == 'id'){
				$scope.sort[0] = true;
			}
			else if(sort == 'name'){
				$scope.sort[1] = true;
			}
			else if(sort == 'bidStartDate'){
				$scope.sort[2] = true;
			}
			else if(sort == 'bidEndDate'){
				$scope.sort[3] = true;
			}
			else if(sort == 'companyName'){
				$scope.sort[4] = true;
			}
		}
		selectBidList();
	}

	// 전체 조회
	let selectBidList = function() {

		let params = {
			"id": $scope.id ? $scope.id : null,
			"bidStartDate": $scope.bidStartDate ? dateHandling.dateToLong($scope.bidStartDate) : null,
			"bidEndDate": $scope.bidEndDate ? dateHandling.dateToLong($scope.bidStartDate) : null,
			"bidName": $scope.bidName ? $scope.bidName : null,
			"manufacturerName": $scope.manufacturerName ? $scope.manufacturerName : null,
			"status": $scope.status ? $scope.status : null,
			"orderby": $scope.orderby.order ? $scope.orderby.order : null,
			"desc": $scope.orderby.desc,
			"pageNum": $scope.pagination.pageNum,
			"pageItemPerPage": $scope.pagination.pageItemPerPage
		}

		Factory.getBidList($scope, params, bidResource, $rootScope, dateHandling);
	}
});