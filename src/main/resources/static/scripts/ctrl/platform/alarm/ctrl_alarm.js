platform.controller('AlarmController', function($scope, $resource, $uibModal, Factory) {

	// date 팩토리
	let dateFactory = Factory.dateHandling;

	// 공통 method 팩토리
	let commonFactory = Factory.common;

	let alarmRes = $resource(
		"alarms/",
		null,
		{
			select: {
				method: 'GET'
			}
		}
	)


	// ------------------------------- Scope -------------------------------
	$scope.receiveId = "";
	$scope.order = "receiveDate";
	$scope.startDate = 0;
	$scope.endDate = 0;
	$scope.itemCount = 15;
	$scope.desc = "fasle";


	$scope.select = function() {
		alarmRes.select(
			{}
			, {
				receiveId: $scope.receiveId,
				startDate: dateFactory.dateToLong($scope.startDate),
				endDate: dateFactory.dateToLong($scope.endDate),
				itemCount: $scope.itemCount,
				pageNum: $scope.pageNum,
				pageItemPerPage: $scope.pageItemPerPage,
				order: $scope.order,
				desc: $scope.desc
			}
			, function(alarmRes) {
				$scope.alarms = alarmRes.data;
			}
			, function() {

			}
		)
	}
	$scope.select();

	// 데이터 정렬
	$scope.sortData = function(order) {
		$scope.reverseSort = !$scope.reverseSort;
		$scope.order = order;
		$scope.desc = $scope.reverseSort
		$scope.sort = [false, false, false, false];
		if (order == 'receiveDate') {
			$scope.sort[0] = true;
		}
		else if (order == 'industryType') {
			$scope.sort[1] = true;
		}
		else if (order == 'name') {
			$scope.sort[2] = true;
		}
		else if (order == 'contents') {
			$scope.sort[3] = true;
		}
		$scope.select();
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