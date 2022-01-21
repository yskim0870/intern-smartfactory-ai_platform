platform.factory("Factory", function($resource) {

	return {
		bidResource: $resource(
			'/bids/:param1/:param2',
			null,
			{
				"createBidNotice": {
					"method": "POST",
					"headers": { enctype: 'multipart/form-data' }
				},
				"getBidList": {
					"method": "GET",
					"param1": null
				},
				"getBidDetail": {
					"method": "GET",
					"param1": null
				},
				"getExpertList": {
					"method": "GET",
					"param1": null
				},
				"getExpertManager": {
					"method": "GET",
					"param1": null,
					"param2": null
				},
				"getCompanyInfo": {
					"method": "GET",
					"param1": null,
					"param2": null
				},
				"updateBid": {
					"method": "PATCH",
					"param": null
				}
			}
		),

		// ---------------------- 대시보드 -----------------------------

		dashboardResource: $resource(
			'/dashboard/:param',
			null,
			{
				"getDashBoardCount": {
					"method": "GET"
				},
				"getEdgeCount": {
					"method": "GET",
					"param": null
				}
			}
		),

		// ---------------------- 제조사 관리 -----------------------------

		manuResource: $resource(
			'/manufacturer/:param',
			null,
			{
				// TODO 업태, 업종에 대한 처리 (모두 출력)
				"getCondition": {
					"method": "GET",
					"param": null
				},

				// TODO 제조사 목록 조회
				"getManuList": {
					"method": "GET",
					"param": null
				},

				// TODO 제조사 상세보기 조회	
				"getManuDetail": {
					"method": "GET",
					"param": null
				}
			}
		),


		// ---------------------- 전문업체 관리 -----------------------------




		// ---------------------- 입찰 공고 조회 -----------------------------

		getBidList: function($scope, params, bidResource, $rootScope) {

			// ------------------ date 처리 ------------------
			// radio 박스 체크 처리에 필요한 객체
			// value = 1,3,6개월 전에 대한 값-1,3,6
			$scope.dateRadio = {
				"value": 0,
				"isChecked": [false, false, false],
			}

			// 1,3,6 개월 라디오 박스에 대한 처리
			let prevMonth = function(month) {
				let d = new Date();
				let nowMonth = d.getMonth();

				d.setMonth(nowMonth - month);

				return d;
			}

			// date 객체 2022.1.2 -> 2022.01.02 포맷
			let dateFormat = function(date) {
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				let format = year + "-" + (("00" + month.toString()).slice(-2)) + "-" + (("00" + day.toString()).slice(-2));

				return format;
			}

			// long 객체 -> date
			let longToDate = function(date) {
				return dateFormat(new Date(date));
			}

			// 1,3,6개월 전 라디오 박스 클릭 시
			$scope.dateRadioClick = function(num) {

				if (num == 1) {
					$scope.dateRadio.isChecked[0] = true;
				}
				else if (num == 3) {
					$scope.dateRadio.isChecked[1] = true;
				}
				else if (num == 6) {
					$scope.dateRadio.isChecked[2] = true;
				}

				$scope.bidStartDate = prevMonth(num);
				$scope.bidEndDate = new Date();
			}

			$scope.dateChange = function() {
				for (let i = 0; i < $scope.dateRadio.isChecked.length; i++) {
					$scope.dateRadio.isChecked[i] = false;
				}
			}

			let item = ""

			// ------------------ 목록 조회 ------------------
			bidResource.getBidList(
				params,
				{
					"userID": function() {
						if ($scope.userType == 1) {
							return $rootScope.authentication.userID;
						}
						else {
							return null;
						}
					}
				},
				// success
				function(res) {
					if ($scope.dash != 1) {
						$scope.pagination.totalCount = res.data.totalCount;
						$scope.pagination.itemPerPage = Math.ceil($scope.pagination.totalCount / $scope.pagination.pageItemPerPage.value);
					}
					$scope.items = res.data.items;

					for (let i = 0; i < $scope.items.length; i++) {
						$scope.items[i].bidInfo.isContracted = true;
						$scope.items[i].bidInfo.bidStartDate = longToDate($scope.items[i].bidInfo.bidStartDate);
						$scope.items[i].bidInfo.bidEndDate = longToDate($scope.items[i].bidInfo.bidEndDate);
						$scope.items[i].detailStatus = false;
						$scope.items[i].bidInfo.contractDate = //
							$scope.items[i].bidInfo.contractDate ? longToDate($scope.items[i].bidInfo.contractDate) : null;

						// status 설정 - 계약일자의 유무를 판단하여 있을경우 상태를 계약완료로 바꿈
						if ($scope.items[i].bidInfo.contractDate) {
							$scope.items[i].bidInfo.status = 2;
							$scope.items[i].bidInfo.isContracted = false;
						}
						else {
							$scope.items[i].bidInfo.isContracted = true;
						}
					}
				},
				// fail
				function(res) {
					alert(res);
				}
			);

			// ------------------ detail ------------------

			let showDetail = function(item, id) {

				bidResource.getBidDetail(
					{ "param1": id },
					null,
					function(res) {
						$scope.bid = res.data;
						$scope.bid.bidInfo.bidStartDate = longToDate(res.data.bidInfo.bidStartDate);
						$scope.bid.bidInfo.bidEndDate = longToDate(res.data.bidInfo.bidEndDate);
						item.detailStatus = !item.detailStatus;
					},
					function(res) {
						alert(res);
					}
				);
			}

			// 공고 목록 클릭시
			$scope.selectDetail = function(item, id) {
				// 상세보기 조회
				showDetail(item, id);
			}
		}
	}
});