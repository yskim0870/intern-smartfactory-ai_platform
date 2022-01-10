platform.controller('EdgeController', function($log, $scope, $resource, $uibModal) {

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

	$scope.getEdges = function(managerId, startDate, endDate, itemCount, pageNum, pageItemPerPage, order, desc) {
		res.getEdges(
			{
				managerId: managerId,
				startDate: dateToLong(startDate),
				endDate: dateToLong(endDate),
				itemCount: itemCount,
				pageNum: pageNum,
				pageItemPerPage: pageItemPerPage,
				order: order,
				desc: desc
			}
			, {}
			, function(res) {
				$scope.edgeGWs = res;
			}
			, function() {

			}
		);
	}

	$scope.reverseSort = false;

	$scope.sortData = function() {
		$scope.reverseSort = !$scope.reverseSort;
	}


	$scope.getEdges(null, 0, 0, 15, 1, 0);
	$scope.desc = false;


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

			}
		)
	};

	$scope.deleteEdge = function(id) {
		res.deleteEdge(
			{ val: id }
			, {}
			, function() {
				alert("삭제?");
			}
			, function() {
				alert("delete fail");
			}
		)
	};

	// recent month option
	$scope.isChecked = false;

	$scope.dateRadioClick = function(num) {
		if (num == 1) {
			$scope.isChecked[0] = true;
		} else if (num == 3) {
			$scope.isChecked[1] = true;
		} else if (num == 6) {
			$scope.isChecked[2] = true;
		}

		$scope.option.startDate = recentMonth(num);
		console.log($scope.startDate);

		$scope.option.endDate = new Date();
	};

	let recentMonth = function(num) {
		let month = new Date();
		month.setMonth(month.getMonth() - num);

		return month;
	};

	$scope.dateChange = function() {
		for (let check = 0; check < $scope.dateRadio.isChecked.length; check++) {
			$scope.dateRadio.isChecked[check] = false;
		}
	};

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

	// 데이터 건수 default 값
	$scope.option = {
		itemCount: $scope.items[0].value
	};

	// 상세 보기
	$scope.clickHandler = function(edgeInfo) {
		edgeInfo.show = !edgeInfo.show;
	};



	//	pagination
	$scope.pageNum = 1;
	$scope.maxSize = 5;
	$scope.bigTotalItems = 175;
	$scope.bigCurrentPage = 1;
	$scope.setPage = function(pageNum) {
		$scope.pageNum = pageNum;
	};

	// Modal 
	$scope.createModal = function(id, startDate, endDate, updateDate, port, host, status) {

		let addInstance = $uibModal.open({
			templateUrl: '/static/templates/platform/edgegw/modal_edgegw_add.html',
			controller: 'EdgeModalCtrl',
			size: "md",
			resolve: {
				id: function() {
					return id;
				},
				startDate: function() {
					return startDate;
				},
				endDate: function() {
					return endDate;
				},
				updateDate: function() {
					return updateDate;
				},
				port: function() {
					return port;
				},
				host: function() {
					return host;
				},
				status: function() {
					return status
				}
			}
		})

		addInstance.result.then(
			function() {
				// 모달창 종료 close
			}, function() {
				// 여기가 dismiss
			});
	};

	$scope.updateModal = function(id, startDate, endDate, updateDate, port, host, status) {

		let modifyInstance = $uibModal.open({
			templateUrl: '/static/templates/platform/edgegw/modal_edgegw_modify.html',
			controller: 'EdgeModalCtrl',
			size: "md",
			resolve: {
				id: function() {
					return id;
				},
				startDate: function() {
					return new Date(startDate);
				},
				endDate: function() {
					return new Date(endDate);
				},
				updateDate: function() {
					return updateDate;
				},
				port: function() {
					return port;
				},
				host: function() {
					return host;
				},
				status: function() {
					return status;
				}
			}
		})

		modifyInstance.result.then(
			function() {
				// 모달 close
			}, function() {
				// 모달 dismiss
			});
	};

});


