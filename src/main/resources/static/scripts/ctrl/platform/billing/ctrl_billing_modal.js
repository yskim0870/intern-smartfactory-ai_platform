platform.controller('BillingModalCtrl', function($resource, $scope, $uibModalInstance) {

	let res = $resource(
		{}
	);

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

	//  close and dismiss ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	$scope.ok = function() {
		$uibModalInstance.close();
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss();
	}
	
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ close and dismiss
});