platform.controller("BidCtrl", function($scope, Factory, $rootScope) {

	let bidResource = Factory.bidResource;
	let dateHandling = Factory.dateHandling;
	
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

	// date객체 -> long
	let dateToLong = function(date) {
		return new Date(date).valueOf();
	}

	// ------------------ select bid list ------------------

	// 검색 버튼 클릭 시 검색 조건에 맞게 조회
	$scope.showBidView = function(id, bidStartDate, bidEndDate, bidName, manufacturerName//
		, status, orderby, desc) {

		let startDate = dateToLong(bidStartDate);
		let endDate = dateToLong(bidEndDate);

		selectBidList(id, startDate, endDate, bidName, manufacturerName//
			, status, orderby, desc);
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
		selectBidList();
	}

	// 전체 조회
	let selectBidList = function(id, bidStartDate, bidEndDate, bidName, manufacturerName//
		, status) {

		let params = {
			"id": id ? id : null,
			"bidStartDate": bidStartDate ? bidStartDate : null,
			"bidEndDate": bidEndDate ? bidEndDate : null,
			"bidName": bidName ? bidName : null,
			"manufacturerName": manufacturerName ? manufacturerName : null,
			"status": status ? status : null,
			"orderby": $scope.orderby.order ? $scope.orderby.order : null,
			"desc": $scope.orderby.desc ? $scope.orderby.desc : null,
			"pageNum": $scope.pagination.pageNum,
			"pageItemPerPage": $scope.pagination.pageItemPerPage
		}

		Factory.getBidList($scope, params, bidResource, $rootScope, dateHandling);
	}
});