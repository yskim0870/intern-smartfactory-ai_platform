platform.controller('EdgeModalCtrl', function($resource, $scope, $uibModalInstance, id) {

	// RESOURCE  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
	let res = $resource(
		"edge-gws/:val",
		null,
		{
			createEdge: {
				method: 'PUT',
				params: {
					id: "",
					managerId: "",
					startDate: "",
					endDate: "",
					updateDate: "",
					host: "",
					port: "",
					status: ""
				}
			},
			updateEdge: {
				method: 'PATCH',
				params: { val: "" }
			}
		}
	);

	let userRes = $resource(
		"users/:val",
		null,
		{
			getUser: {
				method: 'GET',
				params: { val: "" }
			}
		}
	);

	let company = $resource(
		"company/:val",
		null,
		{
			getCompany: {
				method: 'GET'
			}
		}
	);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ RESOURCE 
	$scope.id = id;
	$scope.startDate = 0;
	$scope.endDate = 0;


	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ  SCOPE

	// CRUD ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	// 회사 목록 조회
	$scope.getCompany = function() {
		company.getCompany(
			{}
			, {}
			, function(company) {
				$scope.companies = company.data.items;
			}, function() {

			}
		)
	};
	$scope.getCompany();


	// 담당자 정보 조회
	$scope.getUser = function(id) {
		userRes.getUser(
			{
				val: id
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
				status: $scope.status,
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
	$scope.updateEdge = function(managerId) {
		res.updateEdge(
			{
				val: $scope.id
			}
			, {
				managerId: managerId,
				startDate: dateToLong($scope.startDate),
				endDate: dateToLong($scope.endDate),
				updateDate: dateToLong(new Date())
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

	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ CRUD 

	//  close and dismiss ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

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
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ close and dismiss

	// date to long 형변환
	function dateToLong(date) {
		if (date != null) {

			return new Date(date).valueOf();
		}
	};

	// 사용자 권한확인
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