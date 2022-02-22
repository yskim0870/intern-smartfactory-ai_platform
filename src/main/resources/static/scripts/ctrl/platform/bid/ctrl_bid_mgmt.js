platform.controller("BidMgmtController", function($scope, $uibModal, $rootScope, Factory) {

	$scope.userGrade = $rootScope.authentication.userGrade;
	
	// 대시보드에서 입찰공고 목록을 조회하기 위해 별도로 사용하는 변수
	$scope.dash = 0;

	let bidResource = Factory.bidResource;
	let dateHandling = Factory.dateHandling;

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
	$scope.getBidView = function() {
		selectBidList();
		$scope.id = null;
		$scope.bidStartDate = null;
		$scope.bidEndDate = null;
		$scope.bidName = null;
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
			$scope.sort = [false, false, false, false];
			
			if(sort == 'id'){
				$scope.sort[0] = true;
			}
			else if(sort == 'name'){
				$scope.sort[1] = true;
			}
			else if(sort == 'contractDate'){
				$scope.sort[2] = true;
			}
			else if(sort == 'status'){
				$scope.sort[3] = true;
			}
		}
		selectBidList();
	}
	
	// 제조사 아이디
	let id = $rootScope.authentication.userID;
	
	// 전체 조회
	let selectBidList = function() {

		let params = {
			"userGrade": $scope.userGrade,
			"userID": id, // 제조사 아이디 $rootScope.authentication.userID
			"id": $scope.id ? $scope.id : null,
			"bidStartDate": $scope.bidStartDate ? dateHandling.dateToLong($scope.bidStartDate) : null,
			"bidEndDate": $scope.bidEndDate ? dateHandling.dateToLong($scope.bidStartDate) : null,
			"bidName": $scope.bidName ? $scope.bidName : null,
			"status": $scope.status ? $scope.status : null,
			"orderby": $scope.orderby.order ? $scope.orderby.order : null,
			"desc": $scope.orderby.desc,
			"pageNum": $scope.pagination.pageNum,
			"pageItemPerPage": $scope.pagination.pageItemPerPage
		}
		
		Factory.getBidList($scope, params, bidResource, $rootScope, dateHandling);
	}

	// 회사명
	let name = null;

	// ---------------------- 회사명 조회 ---------------------- //
	bidResource.getCompanyInfo(
		{
			"param1": "company",
			"param2": id // 제조사 아이디 $rootScope.authentication.userID
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
				console.log('cancel');
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
				console.log('cancel');
			}
		);
	}
});