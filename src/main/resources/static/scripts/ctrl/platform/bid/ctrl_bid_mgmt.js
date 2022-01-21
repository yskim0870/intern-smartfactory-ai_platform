platform.controller("BidMgmtController", function($scope, $uibModal, $rootScope, Factory) {

	$scope.userGrade = $rootScope.authentication.userGrade;
	let id = $rootScope.authentication.userID;
	console.log(id);
	let name = null;
	
	$scope.dash = 0;

	let bidResource = Factory.bidResource;

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
			console.log($scope.orderby.desc);
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
			"pageItemPerPage": $scope.pagination.pageItemPerPage,
			"userType": $scope.userType ? $scope.userType : null
		}

		Factory.getBidList($scope, params, bidResource, $rootScope);
	}


	// ---------------------- 회사명 조회 ---------------------- //
	bidResource.getCompanyInfo(
		{
			"param1": "company",
			"param2": id // 제조사 아이디
		},
		null,
		function(res) {
			name = res.data.name;
		},
		function(res) {
			alert(res);
		}
	);

	// ---------------------- 입찰등록 ---------------------- //
	$scope.createBidBtnClick = function() {
		let modalInstance = $uibModal.open({
			templateUrl: "/static/templates/platform/bid/create_bid_modal.html",
			controller: "CreateBidController",
			size: "md",
			resolve: {
				companyName: function() {
					return name;
				}
			}
		});

		// Modal 이 닫혔을때(ok, cancel) 받는 이벤트 부분
		modalInstance.result.then(
			function(bid) { // ok
				console.log("modal result : " + bid);
				selectBidList();
			},
			function() { // cancel
				console.log('modal에서 dismissed at: ' + new Date());
			}
		);
	}

	// ---------------------- 계약등록 ---------------------- //

	$scope.contractBtnClick = function(id) {

		let modalInstance = $uibModal.open({
			templateUrl: "/static/templates/platform/bid/contract_bid_modal.html",
			controller: "ContractBidController",
			size: "md",
			resolve: {
				bidID: id
			}
		});

		// Modal 이 닫혔을때(ok, cancel) 받는 이벤트 부분
		modalInstance.result.then(
			function(contractInfo) { // ok
				console.log("modal result : " + contractInfo);
				selectBidList();
			},
			function() { // cancel
				console.log('modal에서 dismissed at: ' + new Date());
			}
		);
	}
});