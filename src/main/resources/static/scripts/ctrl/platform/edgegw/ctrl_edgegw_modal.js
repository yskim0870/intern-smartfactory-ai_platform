platform.controller('EdgeModalCtrl', function($resource, $scope, $uibModalInstance, id) {

	// ------------------------------- Resource -------------------------------
	let res = $resource(
		"edge-gws/:val",
		null,
		{
			createEdge: {
				method: 'PUT'
			},
			updateEdge: {
				method: 'PATCH'
			}
		}
	);

	let userRes = $resource(
		"users/:param1/:param2",
		null,
		{
			getUser: {
				method: 'GET'
			}
		}
	);

	let companyRes = $resource(
		"users/:val",
		null,
		{
			getCompany: {
				method: 'GET'
			}
		}
	);


	// ------------------------------- Scope -------------------------------
	$scope.companyInfo ="";
	$scope.id = id;
	$scope.startDate = 0;
	$scope.endDate = 0;


	// ------------------------------- CRUD -------------------------------
	// 회사 목록 조회
	$scope.getCompany = function() {
		companyRes.getCompany(
			{
				val: 1
			}
			, {
				pageNum: null,
				pageItemPerPage: null
			}
			, function(companyRes) {
				$scope.companies = companyRes.data.items;
			}, function() {

			}
		)
	};
	$scope.getCompany();


	// 담당자 정보 조회
	$scope.getUser = function(id) {
		userRes.getUser(
			{
				param1: 1,
				param2: id
			}
			, {}
			, function(userRes) {
				$scope.user = userRes.data;
			}
			, function() {

			}
		)
	}

	// EdgeGateway 등록
	$scope.createEdge = function(managerId) {
		res.createEdge(
			{}
			, {
				id: $scope.id,
				managerId: managerId,
				startDate: dateToLong($scope.startDate),
				endDate: dateToLong($scope.endDate),
				host: $scope.host,
				port: $scope.port,
				status: changeStatus($scope.status),
				updateDate: dateToLong(new Date()),
			}
			, function() {
				alert("등록 완료");
				$scope.ok();
			}
			, function() {
				alert("중복된 아이디입니다.");
			}
		)
	};

	// EdgeGateway 수정
	$scope.updateEdge = function() {
		res.updateEdge(
			{
				val: $scope.id
			}
			, {
				managerId: $scope.user.companyInfo.name,
				startDate: dateToLong($scope.startDate),
				endDate: dateToLong($scope.endDate),
				updateDate: dateToLong(new Date()),
				
				
			}
			, function() {
				alert("수정 완료");
				$scope.ok();

			}
			, function() {
				alert("update fail");
			}
		)
	};


	// ------------------------------- Close / Dismiss -------------------------------
	$scope.ok = function() {
		$uibModalInstance.close();
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	}

	// close, dismiss 선택시 재질문
	$scope.createConfirm = function(managerId) {
		if (confirm("정말로 등록하시겠습니까?")) {
			$scope.createEdge(managerId);
		}
	};

	// close, dismiss 선택시 재질문	
	$scope.updateConfirm = function(managerId) {
		if (confirm("정말로 수정하시겠습니까?")) {
			$scope.updateEdge(managerId);
		}
	}

	// ------------------------------- Method -------------------------------
	// date to long 형변환
	function dateToLong(date) {
		if (date != null) {

			return new Date(date).valueOf();
		}
	};
	
	function changeStatus(status){
		if(status ="작동"){
			return 1
		} else if(status ="미작동"){
			return 0
		}
	};

	// 사용자 권한확인 -- 제조사 정보
	$scope.gradeCheck = function(grade) {
		if (grade == 0) {
			$scope.gradeAdmin = true;
			$scope.gradeCompany = false;
		} else if (grade == 1) {
			$scope.gradeAdmin = false;
			$scope.gradeCompany = true;
			$scope.getUser(AUTHENTICATION.userID);
		}
	};
	$scope.gradeCheck(AUTHENTICATION.grade);

	$scope.statusItems = [
		{ value: 0, display: "미작동" },
		{ value: 1, display: "작동" }
	];
	$scope.status = $scope.statusItems[0];
});