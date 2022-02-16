platform.controller('EdgeController', function($scope, $resource, $uibModal, Factory) {

	let dateFactory = Factory.dateHandling;
	let commonFactory = Factory.common;


	// ------------------------------- Resource -------------------------------
	let res = $resource(
		"edge-gws/:val",
		null,
		{
			getEdges: {
				method: 'GET'
			},

			getEdge: {
				method: 'GET'
			},
			deleteEdge: {
				method: 'DELETE'
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


	// ------------------------------- Scope -------------------------------
	$scope.name = "";
	$scope.order = "id";
	$scope.startDate = 0;
	$scope.endDate = 0;
	$scope.pageNum = 1;
	$scope.pageItemPerPage = 1;
	$scope.itemCount = 15;
	$scope.desc = false;
	$scope.isChecked = false;
	$scope.reverseSort = false;


	// ------------------------------- DB -------------------------------
	// EdgeGateway 목록 조회
	$scope.getEdges = function() {
		res.getEdges(
			{
				managerId: $scope.name,
				startDate: dateFactory.dateToLong($scope.startDate),
				endDate: dateFactory.dateToLong($scope.endDate),
				itemCount: $scope.itemCount,
				pageNum: $scope.pageNum,
				pageItemPerPage: $scope.pageItemPerPage,
				order: $scope.order,
				desc: $scope.desc
			}
			, {}
			, function(res) {
				$scope.edgeGWs = res;
			}
			, function() {

			}
		);
	}

	// EdgeGateway 상세 보기
	$scope.getEdge = function(edgeInfo) {
		res.getEdge(
			{
				val: edgeInfo.id,
			}
			, {}
			, function(res) {
				$scope.edgeGW = res.data.company;
				$scope.clickHandler(edgeInfo);
			}
			, function() {
				alert("select fail");
			}
		)
	};

	// EdgeGateway 삭제
	$scope.deleteEdge = function(id) {
		res.deleteEdge(
			{ val: id }
			, {}
			, function() {
				$scope.getEdges();
				alert("삭제하였습니다.");
			}
			, function() {
				alert("delete fail");
			}
		)
	};

	// userID로 기업명 호출
	$scope.getUser = function(id) {
		userRes.getUser(
			{
				val: id
			}
			, {}
			, function(userRes) {
				$scope.name = userRes.data.companyInfo.name;
			}
			, function() {

			}
		)
	}


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


	// ------------------------------- Method -------------------------------
	// 데이터 건수 option
	$scope.items = [
		{ value: 15, display: "15개 보기" },
		{ value: 30, display: "30개 보기" },
	];


	// 상세 보기 접기 / 펼치기
	$scope.clickHandler = function(edgeInfo) {
		commonFactory.lookDetail(edgeInfo);
	};

	// 데이터 정렬
	$scope.sortData = function(order) {
		commonFactory.sortData($scope, order);
		$scope.getEdges();
	}

	// 작동 상태
	$scope.boolStatus = function(status) {
		if (status == 0) {
			return "N";
		} else {
			return "Y";
		}
	}

	// 삭제 확인
	$scope.deleteConfirm = function(id) {
		if (confirm("정말로 삭제하시겠습니까?")) {
			$scope.deleteEdge(id);
		}
	};

	// 권한 확인
	$scope.checkGrade = function() {
		if (AUTHENTICATION.grade == 0) {
			return false;
		} else if (AUTHENTICATION.grade == 1) {
			$scope.getUser(AUTHENTICATION.userID);
			$scope.name = AUTHENTICATION.userID;
			return true;
		}
	};
	$scope.checkGrade();

	$scope.getEdges();


	// ------------------------------- Modal -------------------------------
	// EdgeGateway 등록 Modal
	$scope.createModal = function(id) {

		let addInstance = $uibModal.open({
			templateUrl: '/static/templates/platform/edgegw/modal_edgegw_add.html',
			controller: 'EdgeModalCtrl',
			size: "md",
			resolve: {
				id: function() {
					return id;
				}
			}
		});

		addInstance.result.then(
			function() {
				$scope.getEdges();
				// 모달창 종료 close
			}, function() {
				// 여기가 dismiss
			});
	};

	// EdgeGateway 수정 Modal
	$scope.updateModal = function(id) {

		let modifyInstance = $uibModal.open({
			templateUrl: '/static/templates/platform/edgegw/modal_edgegw_modify.html',
			controller: 'EdgeModalCtrl',
			size: "md",
			resolve: {
				id: function() {
					return id;
				}
			}
		})

		modifyInstance.result.then(
			function() {
				$scope.getEdges();
			}, function() {
				// 모달 dismiss
			});
	};
});


