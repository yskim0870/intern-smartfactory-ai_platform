platform.controller('AlarmController', function($scope, $resource, $uibModal, Factory) {

	let dateFactory = Factory.dateHandling;


	let alarmRes = $resource(
		"alarms/",
		null,
		{
			select: {
				method: 'GET'
			}
		}
	)
	$scope.itemCount = 15;


	$scope.select = function() {
		alarmRes.select(
			{
				receiveId: "",
				startDate: dateFactory.dateToLong($scope.startDate),
				endDate: dateFactory.dateToLong($scope.endDate),
				itemCount: $scope.itemCount,
				pageNum: $scope.pageNum,
				pageItemPerPage: $scope.pageItemPerPage,
				order: $scope.order,
				desc: $scope.desc
			}
		)
	}


	// ------------------------------- Date -------------------------------
	// 클릭 시 true로 변경
	$scope.dateRadioClick = function(num) {
		dateFactory.dateRadioClick($scope, num);
	};
	// false로 초기화 및 길이 설정
	$scope.dateRadio = {
		isChecked: [false, false, false]
	};

	// 날짜 변경
	$scope.dateChange = function() {
		dateFactory.dateChange($scope);
	};

	// 데이터 건수 option
	$scope.items = [
		{ value: 15, display: "15개 보기" },
		{ value: 30, display: "30개 보기" }
	];


	// ------------------------------- Modal -------------------------------
	// Alarm 등록 Modal
	$scope.createModal = function() {

		let addInstance = $uibModal.open({
			templateUrl: '/static/templates/platform/alarm/modal_alarm_send.html',
			controller: 'AlarmModalCtrl',
			size: "md",
			resolve: {
			}
		});

		addInstance.result.then(
			function() {
				// 모달창 종료 close
			}, function() {
				// 여기가 dismiss
			});
	};


});