platform.controller('EdgeController', function($scope, $resource, $uibModal) {

	// Resource ----------------------------------------------------------------
	let res = $resource(
		"edge-gws/:val",
		null,
		{
			getEdges: {
				method: 'GET',
				params: {
					managerId: "",
					startDate: "",
					endDate: "",
					itemCount: "",
					pageNum: "",
					pageItemPerPage: "",
					order: ""
				}
			},

			getEdge: {
				method: 'GET',
				params: { val: "" }
			},
			deleteEdge: {
				method: 'DELETE',
				params: { val: "" }
			}
		}
	);
	// ---------------------------------------------------------------- Resource


	// Scope ----------------------------------------------------------------
	$scope.name = "";
	$scope.order = "id";
	$scope.startDate = 0;
	$scope.endDate = 0;
	$scope.pageNum = 1;
	$scope.pageItemPerPage = 1;
	$scope.itemCount = 15;
	$scope.pageNum = 1;
	$scope.maxSize = 5;
	$scope.bigTotalItems = 175;
	$scope.bigCurrentPage = 1;
	$scope.desc = false;
	$scope.isChecked = false;
	$scope.reverseSort = false;

	// ---------------------------------------------------------------- Scope


	// DB 기능 ----------------------------------------------------------------
	$scope.getEdges = function() {
		res.getEdges(
			{
				managerId: $scope.name,
				startDate: dateToLong($scope.startDate),
				endDate: dateToLong($scope.endDate),
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
	$scope.getEdges($scope.order, $scope.desc);



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

	$scope.deleteEdge = function(id) {
		res.deleteEdge(
			{ val: id }
			, {}
			, function() {
				$scope.getEdges(null, 0, 0, 15, 1, 0);
				alert("삭제하였습니다.");
			}
			, function() {
				alert("delete fail");
			}
		)
	};
	// ---------------------------------------------------------------- DB 기능 

	// Recent Date ----------------------------------------------------------------
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

	// 데이터 정렬
	$scope.sortData = function(order) {
		$scope.reverseSort = !$scope.reverseSort;
		$scope.order = order;
		$scope.desc = $scope.reverseSort
		$scope.getEdges();
	}

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


	// Method ----------------------------------------------------------------
	// Date To UnixTimestamp
	function dateToLong(date) {
		if (date != null) {
			console.log(new Date(date).valueOf());
			return new Date(date).valueOf();
		}
	};

	// 데이터 건수 option
	$scope.items = [
		{ value: 15, display: "15개 보기" },
		{ value: 30, display: "30개 보기" },
	];

	// 상세 보기 접기 / 펼치기
	$scope.clickHandler = function(edgeInfo) {
		edgeInfo.show = !edgeInfo.show;
	};

	//	pagination
	$scope.setPage = function(pageNum) {
		$scope.pageNum = pageNum;
	};

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
	// ---------------------------------------------------------------- Method


	// Modal ----------------------------------------------------------------
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
				$scope.getEdges($scope.itemCount, $scope.pageNum);
				// 모달창 종료 close
			}, function() {
				// 여기가 dismiss
			});
	};

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
				$scope.getEdges($scope.itemCount, $scope.pageNum);
			}, function() {
				// 모달 dismiss
			});
	};
	// ---------------------------------------------------------------- Modal
});


