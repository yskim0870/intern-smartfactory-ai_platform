platform.controller('AlarmController', function($scope) {

	$scope.itemCount = 15;

	$scope.dateRadioClick = function(num) {
		if (num == 0) {
			$scope.date = new Date();
			$scope.isChecked[0] = true;
		} else if (num == 1) {
			$scope.isChecked[1] = true;
		}
	}
	// 날짜 변경
	$scope.dateChange = function() {
		for (let check = 0; check < $scope.dateRadio.isChecked.length; check++) {
			$scope.dateRadio.isChecked[check] = false;
		}
	};

	// 데이터 건수 option
	$scope.items = [
		{ value: 15, display: "15개 보기" },
		{ value: 30, display: "30개 보기" }
	];

});