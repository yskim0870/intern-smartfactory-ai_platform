platform.controller("DashboardBidDetailCtrl", function($scope, $uibModalInstance, id, Factory) {

	// date 객체 2022.1.2 -> 2022.01.02 포맷
	let dateFormat = function(date) {
		let year = date.getFullYear();
		let month = date.getMonth() + 1;
		let day = date.getDate();
		let format = year + "-" + (("00" + month.toString()).slice(-2)) + "-" + (("00" + day.toString()).slice(-2));

		return format;
	}

	// long 객체 -> date
	let longToDate = function(date) {
		return dateFormat(new Date(date));
	}

	// 상세보기 정보 조회
	Factory.bidResource.getBidDetail(
		{ "param1": id },
		null,
		function(res) {
			$scope.bid = res.data;
			$scope.bid.bidInfo.bidStartDate = longToDate(res.data.bidInfo.bidStartDate);
			$scope.bid.bidInfo.bidEndDate = longToDate(res.data.bidInfo.bidEndDate);
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