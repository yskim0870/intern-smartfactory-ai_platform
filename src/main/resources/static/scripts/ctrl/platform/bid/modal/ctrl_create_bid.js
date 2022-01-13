platform.controller("CreateBidController", function($uibModalInstance, Upload, $scope, $rootScope, Factory, companyName) {

	$scope.bid = {
		"bidInfo": {
			"bidStartDate": "",
			"bidEndDate": "",
			"bidName": "",
			"prePrice": "",
			"vatIncluded": 0,
			"status": 0
		},
		"manager": {
			"managerID": "",
			"name": "",
			"rank": "",
			"telNumber": "",
			"email": ""
		},
		"files": []
	}

	$scope.bidFiles = [];
	$scope.sampleFiles = [];
	// 입찰공고문 + 버튼 클릭시
	$scope.addBidFiles = function() {
		$scope.bidFiles.push({ fileType: 0 });
	}
	// 샘플데이터 + 버튼 클릭시
	$scope.addSampleFiles = function() {
		$scope.sampleFiles.push({ fileType: 1 });
	}

	// 공고 날짜에 따른 status 변화. -> 0: 대기중, 1: 입찰중, 2: 계약등록시 변화
	let getStatus = function() {
		let status = 0;
		let sDate = $scope.bid.bidInfo.bidStartDate;
		let now = new Date();

		if (sDate > now) {
			status = 0;
		}
		else if (sDate <= now) {
			status = 1;
		}

		return status;
	}

	$scope.companyName = companyName;

	// date객체 -> long
	let dateToLong = function(date) {
		return new Date(date).valueOf();
	}
	
	
	//
	let bidFiles = [];
	let sampleFiles = [];
	//
	$scope.uploadBidFile = function(file) {
		bidFiles.push(file);
	}
	//
	$scope.uploadSampleFile = function(file){
		sampleFiles.push(file);
	}

	// ok button click
	$scope.ok = function(bid) {

		$scope.bid.bidInfo.status = getStatus();

		let bidData = angular.copy(bid);
		bidData.bidInfo.bidStartDate = dateToLong(bidData.bidInfo.bidStartDate);
		bidData.bidInfo.bidEndDate = dateToLong(bidData.bidInfo.bidEndDate);
		bidData.bidInfo.prePrice = bid.bidInfo.prePrice;
		bidData.manager.managerID = $rootScope.authentication.userID;
		
		// 객체 복사 말고 그냥 하나 생성
	//	let bidData = {
	//		bidInfo: {				
	//			bidStartDate: dateToLong(bidData.bidInfo.bidStartDate)
	//		}
	//	}

		Upload.upload({
            url: '/bids',
            method: 'POST',
            data: {
				bidFiles: bidFiles,
				sampleFiles: sampleFiles,
				bid: JSON.stringify(bidData)
			}
        })
        .then(function (resp) {
            console.log('Success uploaded. Response: ' + resp.data);
        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log("");
        });
	};

	// cancel button click
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	}

});