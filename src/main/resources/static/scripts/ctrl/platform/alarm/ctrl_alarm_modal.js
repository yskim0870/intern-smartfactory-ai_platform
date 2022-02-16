platform.controller('AlarmModalCtrl', function($resource, $scope, $uibModalInstance, Factory) {

	$scope.date = 0;

	// 클릭 시 true로 변경
	$scope.dateRadioClick = function(num) {
		if (num == 0) {
			$scope.dateRadio.isChecked[0] = true;
			$scope.date = new Date();
		}
	};
	// false로 초기화 및 길이 설정
	$scope.dateRadio = {
		isChecked: [false]
	};

	// 날짜 변경
	$scope.dateChange = function() {
		for (let i = 0; i < $scope.dateRadio.isChecked.length; i++) {
			$scope.dateRadio.isChecked[i] = false;
		}
	};

	// 데이터 건수 option
	$scope.items = [
		{ value: 15, display: "15개 보기" },
		{ value: 30, display: "30개 보기" }
	];

	// ------------------------------- Close / Dismiss -------------------------------
	// 분석환경 신청 클릭시 재질문
	$scope.applyConfirm = function() {
		if (confirm("정말로 등록하시겠습니까?")) {
			$scope.serviceApply();
		}
	};

	$scope.ok = function() {
		$uibModalInstance.close();
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss();
	}

})