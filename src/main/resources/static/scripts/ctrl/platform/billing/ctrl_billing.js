platform.controller('BillingController', function($scope) {

	// Scope ---------------------------------------------------------------- 
	$scope.companyClass = "";
	$scope.envGrade = "";
	$scope.payStatus = "";
	$scope.approvalStatus = "";
	$scope.companyClass = "";
	$scope.envGrade = "";
	$scope.payStatus = "";
	$scope.approvalStatus = "";
	$scope.startDate = 0;
	$scope.endDate = 0;
	$scope.itemCount = 15;
	$scope.pageNum = 1;
	$scope.maxSize = 5;
	$scope.bigTotalItems = 175;
	$scope.bigCurrentPage = 1;
	$scope.isChecked = false;
	$scope.reverseSort = false;
	// ---------------------------------------------------------------- Scope


	// Recent Date ---------------------------------------------------------------- 
	// 최근 개월 수 선택 후 bind
	$scope.dateRadioClick = function(num) {
		if (num == 1) {
			$scope.isChecked[0] = true;
		} else if (num == 3) {
			$scope.isChecked[1] = true;
		} else if (num == 6) {
			$scope.isChecked[2] = true;
		}

		$scope.startDate = recentMonth(num);

		$scope.endDate = new Date();
	};

	// 현재 개월 수 - 최근 개월
	let recentMonth = function(num) {
		let month = new Date();
		month.setMonth(month.getMonth() - num);

		return month;
	};

	// 날짜 변경
	$scope.dateChange = function() {
		for (let check = 0; check < $scope.dateRadio.isChecked.length; check++) {
			$scope.dateRadio.isChecked[check] = false;
		}
	};
	// ---------------------------------------------------------------- Recent Date


	// Option ----------------------------------------------------------------
	// 데이터 건수 option
	$scope.items = [
		{ value: 15, display: "15개 보기" },
		{ value: 30, display: "30개 보기" }
	];

	// 사용자 분류 옵션
	$scope.companyClassItems = [
		{ value: 1, display: "제조사" },
		{ value: 2, display: "도메인 IT 전문업체" }
	];
	// 환경 등급 옵션
	$scope.envGradeItems = [
		{ value: "Basic" },
		{ value: "Standard" },
		{ value: "Premium" }
	]

	// 납부 상태 옵션
	$scope.payStatusItems = [
		{ value: 0, display: "신청" },
		{ value: 1, display: "납부" },
		{ value: 2, display: "미납" },
		{ value: 3, display: "사용완료" }
	]

	// 승인 상태 옵션
	$scope.approvalStatusItems = [
		{ value: 0, display: "승인 대기" },
		{ value: 1, display: "승인 완료" },
		{ value: 2, display: "승인 거절" }
	]
	// ---------------------------------------------------------------- Option

	// 상세 보기
	$scope.clickHandler = function(edgeInfo) {
		edgeInfo.show = !edgeInfo.show;
	};

	//	pagination
	$scope.setPage = function(pageNum) {
		$scope.pageNum = pageNum;
	};

	// Date To UnixTimestamp
	function dateToLong(date) {
		if (date != null) {
			console.log(new Date(date).valueOf());
			return new Date(date).valueOf();
		}
	};

	// 데이터 정렬
	$scope.sortData = function(order) {
		$scope.reverseSort = !$scope.reverseSort;
		$scope.order = order;
		$scope.desc = $scope.reverseSort
		$scope.getEdges();
	}
});