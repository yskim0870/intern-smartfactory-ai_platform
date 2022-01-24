platform.controller('BillingModalCtrl', function($resource, $scope, $uibModalInstance) {

	let res = $resource(
		"billings/:val",
		null,
		{
			serviceApply: {
				method: 'PUT',
				params: {
					startDate: "",
					endDate: "",
					envGrade: ""
				}
			}
		}
	);
	// CRUD ----------------------------------------------------------------

	$scope.serviceApply = function() {
		res.serviceApply(
			{}
			, {
				startDate: dateToLong($scope.startDate),
				endDate: dateToLong($scope.endDate),
				envGrade: $scope.envGrade
			}
			, function() {
				alert("신청 완료");
				$scope.ok();
			}
			, function() {
				alert("신청 실패");
			}
		)
	};
	// ---------------------------------------------------------------- CRUD

	$scope.envGrade = 0;

	$scope.envGrades = [
		{
			Value: 0,
			Display: "Basic",
			CPU: "1",
			Memory: 1024,
			Storage: 128,
			Price: 10000
		},
		{
			Value: 1,
			Display: "Standard",
			CPU: 2,
			Memory: 2048,
			Storage: 512,
			Price: 20000
		},
		{
			Value: 2,
			Display: "Premium",
			CPU: 3,
			Memory: 4096,
			Storage: 1024,
			Price: 30000
		}
	];

	function dateToLong(date) {
		if (date != null) {

			return new Date(date).valueOf();
		}
	};

	//  close and dismiss ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

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

	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ close and dismiss
});