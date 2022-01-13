platform.controller('EdgeModalCtrl', function($resource, $scope, $uibModalInstance, id) {

	// RESOURCE  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
	let res = $resource(
		"edge-gws/:val",
		null,
		{
			createEdge: {
				method: 'POST',
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

	let manures = $resource(
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
	$scope.host = 0;
	$scope.port = 0;
	$scope.status = 0;

	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ  SCOPE

	//  CRUD ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
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


	$scope.getUser = function(businessNumber) {
		manures.getUser(
			{
				val: businessNumber
			}
			, {}
			, function(manures) {
				$scope.user = manures.data;
			}
			, function() {

			}
		)
	}

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

	$scope.updateConfirm = function(managerId) {
		if (confirm("정말로 수정하시겠습니까?")) {
			$scope.updateEdge(managerId);
		}
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ close and dismiss

	function dateToLong(date) {
		if (date != null) {

			return new Date(date).valueOf();
		}
	};


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
});