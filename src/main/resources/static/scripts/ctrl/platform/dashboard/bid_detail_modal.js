platform.controller("DashboardBidDetailCtrl", function($scope, $uibModalInstance, id, Factory) {

	$scope.bidFiles = [];
	$scope.sampleFiles = [];

	// 상세보기 정보 조회
	Factory.bidResource.getBidDetail(
		{ "param1": id },
		null,
		function(res) {
			$scope.bid = res.data;

/*			$scope.bid.fileList.forEach(function(file){
				if (file.fileType == 0) {
					$scope.bidFiles.push(file);
				}
				else if(file.fileType == 1) {
					$scope.sampleFiles.push(file);
				}
			});*/


			let j = 0;
			let k = 0;
			for (let i = 0; i < $scope.bid.fileList.length; i++) {
				if ($scope.bid.fileList[i].fileType == 0) {
					$scope.bidFiles[j++] = $scope.bid.fileList[i];
				}
				else {
					$scope.sampleFiles[k++] = $scope.bid.fileList[i];
				}
			}

			$scope.bidFileLength = $scope.bidFiles.length;
			$scope.sampleFileLength = $scope.sampleFiles.length;

			$scope.bid.bidInfo.bidStartDate = Factory.dateHandling.longToDate(res.data.bidInfo.bidStartDate);
			$scope.bid.bidInfo.bidEndDate = Factory.dateHandling.longToDate(res.data.bidInfo.bidEndDate);
			$scope.bid.bidInfo.workStartDate = Factory.dateHandling.longToDate(res.data.bidInfo.workStartDate);
			$scope.bid.bidInfo.workEndDate = Factory.dateHandling.longToDate(res.data.bidInfo.workEndDate);
		},
		function(res) {
			alert(res);
		}
	);

	// cancel button click
	$scope.cancel = function() {
		$uibModalInstance.close('cancel');
	}
});