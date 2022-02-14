platform.controller("ContractBidController", function($scope, $rootScope, Factory, bidID, $uibModalInstance) {

	// -------------------- 계약 내용 정보 -------------------- //

	$scope.bid = {
		"bidInfo": {
			// 계약업체 아이디
			"contractorID": "",
			// 계약업체 정보 (CompanyInfoDTO)
			"contractor":{
				"name":"",
				"telNumber":"",
				"email":""
			},
			"workStartDate": "",
			"workEndDate": "",
			"contractPrice": "",
			"contractDate": "",
			"id": bidID
		},
		"company": {
			"name": ""
		}
	}

	let getDetail = function() {
		Factory.bidResource.getBidDetail(
			{ "param2": bidID },
			null,
			function(res) {
				let bid = res.data;
				$scope.bidDetail = {
					"manufacturer": bid.company.name,
					"bidStartDate": new Date(bid.bidInfo.bidStartDate).toLocaleDateString(),
					"bidEndDate": new Date(bid.bidInfo.bidEndDate).toLocaleDateString(),
					"bidName": bid.bidInfo.bidName,
					"manager": bid.manager.name
				}
			},
			function(res) {
				alert(res);
			}
		);
	}

	getDetail();


	// -------------------- 계약등록에 필요한 입력 정보 -------------------- //
	// 사용자의 아이디를 회사 이름으로 가져오고 아이디가 전문업체일 경우 
	// 전문업체 상태를 조회하여 가입승인이 된 경우 해당 회사 정보 표시

	// 계약업체 select box
	Factory.bidResource.getExpertList(
		{ "param1": "experts" },
		null,
		function(res) {
			// 계약업체 리스트
			$scope.experts = res.data;
		},
		function(res) {
			alert(res);
		}
	)

	// 계약 업체에 대한 정보 조회
	// select box change
	$scope.changeExpert = function() {
		Factory.bidResource.getExpertManager(
			{
				"param1": "contract",
				"param2": String($scope.bid.company.name)
			},
			null,
			function(res) {
				$scope.bid.bidInfo.contractorID = res.data.id;
				$scope.bid.bidInfo.contractor.name = res.data.name;
				$scope.bid.bidInfo.contractor.telNumber = res.data.telNumber;
				$scope.bid.bidInfo.contractor.email = res.data.email;
			},
			function(res) {
				alert(res);
			}
		);
	}
	
	// ok button click
	$scope.ok = function() {

		let bidCopy = angular.copy($scope.bid);
		bidCopy.bidInfo.contractDate = Factory.dateHandling.dateToLong($scope.bid.bidInfo.contractDate);
		bidCopy.bidInfo.workStartDate = Factory.dateHandling.dateToLong($scope.bid.bidInfo.workStartDate);
		bidCopy.bidInfo.workEndDate = Factory.dateHandling.dateToLong($scope.bid.bidInfo.workEndDate);
		bidCopy.bidInfo.contractPrice = $scope.bid.bidInfo.contractPrice;
		bidCopy.bidInfo.contractorID = $scope.bid.bidInfo.contractorID;

		Factory.bidResource.updateBid(
			{ "param1": bidID },
			bidCopy,
			function(res) {
				console.log(res);
			},
			function(res) {
				console.log(res);
			}
		);

		$uibModalInstance.close(bidCopy);
	};

	// cancel button click
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	}

});