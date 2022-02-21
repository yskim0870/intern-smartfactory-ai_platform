platform.controller('BillingController', function($scope, $resource, $uibModal, Factory) {

	// date관련 팩토리
	let dateFactory = Factory.dateHandling;

	// 공통 메소드 팩토리
	let commonFactory = Factory.common;

	let res = $resource(
		"billings/:val",
		null,
		{
			selectBillings: {
				method: 'GET',
			},

			detailBilling: {
				method: `GET`,
			},

			approvalBilling: {
				method: 'POST',
			}
		}
	);

	// ------------------------------- Scope ------------------------------- 
	$scope.order = "";
	$scope.companyClass = "";
	$scope.envGrade = "";
	$scope.payStatus = "";
	$scope.approvalStatus = "";
	$scope.companyClass = "";
	$scope.envGrade = "";
	$scope.payStatus = "";
	$scope.approvalStatus = "";
	$scope.name = "";
	$scope.pageItemPerPage = 1;
	$scope.startDate = 0;
	$scope.endDate = 0;
	$scope.itemCount = 15;
	$scope.pageNum = 1;
	$scope.isChecked = false;
	$scope.reverseSort = false;
	$scope.desc = false;


	// ------------------------------- CRUD -------------------------------
	// 과금 목록 조회
	$scope.selectBillings = function() {
		res.selectBillings(
			{}
			, {
				startDate: dateFactory.dateToLong($scope.startDate),
				endDate: dateFactory.dateToLong($scope.endDate),
				name: $scope.companyClass,
				gradeName: $scope.envGrade,
				payStatus: $scope.payStatus,
				approvalStatus: $scope.approvalStatus,
				itemCount: $scope.itemCount,
				pageNum: $scope.pageNum,
				pageItemPerPage: $scope.pageItemPerPage,
				order: $scope.order,
				desc: $scope.desc
			}
			, function(res) {
				$scope.Billings = res;
			}
			, function(res) {
				console.log("실패");
				console.log(res);
			}
		)
	};
	$scope.selectBillings();

	// 과금 상세보기
	$scope.detailBilling = function(billingInfo) {
		res.detailBilling(
			{
				val: billingInfo.id
			}
			, {}
			, function(res) {
				$scope.billing = res;
				$scope.clickHandler(billingInfo);
			}
			, function() {

			}
		)
	};

	// 과금 승인
	$scope.approvalBilling = function(id) {
		res.approvalBilling(
			{
				val: id
			}
			, {}
			, function(res) {
				$scope.billing = res;
				$scope.clickHandler();
			}
			, function() {

			}
		)
	};


	// ------------------------------- Date -------------------------------
	$scope.dateRadioClick = function(num) {
		dateFactory.dateRadioClick($scope, num);
	};

	$scope.dateRadio = {
		isChecked: [false, false, false]
	};

	// 날짜 변경
	$scope.dateChange = function() {
		dateFactory.dateChange($scope);
	};


	// ------------------------------- Options -------------------------------
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

	// ------------------------------- Method -------------------------------
	// 상세 보기
	$scope.clickHandler = function(billingInfo) {
		commonFactory.lookDetail(billingInfo);
	};

	// 데이터 정렬
	$scope.sortData = function(order) {
		$scope.reverseSort = !$scope.reverseSort;
		$scope.order = order;
		$scope.desc = $scope.reverseSort
		$scope.sort = [false, false, false, false, false];
		if (order == 'chargeDate') {
			$scope.sort[0] = true;
		}
		else if (order == 'industryType') {
			$scope.sort[1] = true;
		}
		else if (order == 'name') {
			$scope.sort[2] = true;
		}
		else if (order == 'price') {
			$scope.sort[3] = true;
		}
		else if (order == 'envName') {
			$scope.sort[4] = true;
		}
		else if (order == 'status') {
			$scope.sort[5] = true;
		}
		$scope.selectBillings();
	}

	// 권한 확인 -> 분석환경 신청 버튼
	$scope.checkGrade = function() {
		if (AUTHENTICATION.grade == 1) { // 수정!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			$scope.grade = false;
		} else {
			$scope.grade = true;
		}
	}


	// ------------------------------- Modal ------------------------------- 
	// 분석환경 신청 Modal
	$scope.createModal = function() {

		let addInstance = $uibModal.open({
			templateUrl: '/static/templates/platform/billing/modal_billing_add.html',
			controller: 'BillingModalCtrl',
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

// 작동 상태 Custom Filter
platform.filter('status',
	function() {
		return function(val) {
			return val == 1 ? "작동" : "미작동";
		}
	})