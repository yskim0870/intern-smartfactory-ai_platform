platform.factory("Factory", function($resource) {

	return {
		resource: $resource(
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

		// ---------------------- 입찰 공고 조회 -----------------------------
		getBidList: function($scope, params, resource, $rootScope) {

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

			// long 객체 -> date
			let longToDate = function(date) {
				return new Date(date).toLocaleDateString();
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

			// ------------------ 목록 조회 ------------------
			resource.getBidList(
				params,
				{
					"userID": function() {
						if ($scope.userType == 1) {
							console.log($rootScope.authentication.userID);
							return $rootScope.authentication.userID;
						}
						else {
							return null;
						}
					}
				},
				// success
				function(res) {
					$scope.pagination.totalCount = res.data.totalCount;
					$scope.items = res.data.items;
					console.log($scope.items);
					$scope.pagination.itemPerPage = Math.ceil($scope.pagination.totalCount / $scope.pagination.pageItemPerPage.value);

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
						}
						// status가 계약완료일 경우 계약등록을 더 누르지 못하도록 설정
						if ($scope.items[i].bidInfo.status == 2) {
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
			// TODO html 파일에 파일업로드/다운로드 띄워야함

			let showDetail = function(item, id) {

				resource.getBidDetail(
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