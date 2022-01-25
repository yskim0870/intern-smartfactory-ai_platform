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
				// 계약 업체에 대한 정보 조회 (계약 완료건 상세보기에 계약자 정보)
				"getExpertManager": {
					"method": "GET",
					"param1": null,
					"param2": null
				},
				// 사용자 id로 회사 정보 조회 (회사 이름 조회)
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
			'/dashboard/:param1/:param2',
			null,
			{
				"getDashBoardCount": {
					"method": "GET"
				},
				"getEdgeCount": {
					"method": "GET",
					"param1": null,
					"param2": null
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

		// ---------------------- 날짜 처리 -----------------------------
		dateHandling: {
			// 1,3,6 개월 라디오 박스에 대한 처리
			prevMonth: function(month) {
				let d = new Date();
				let nowMonth = d.getMonth();

				d.setMonth(nowMonth - month);

				return d;
			},

			// date 객체 2022.1.2 -> 2022.01.02 포맷
			dateFormat: function(date) {
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				let format = year + "-" + (("00" + month.toString()).slice(-2)) + "-" + (("00" + day.toString()).slice(-2));

				return format;
			},

			// long 객체 -> date
			longToDate: function(date) {
				return this.dateFormat(new Date(date));
			},

			dateToLong: function(date) {
				return date.valueOf();
			}
		},


		// ---------------------- 전문업체 관리 -----------------------------

		getManuList: function($scope, params, manuResource, $rootScope) {
			manuResource.getManuList(
				params,
				null,
				function() {

				},
				function(res) {
					alert(res);
				}
			);
		},


		// ---------------------- 입찰 공고 조회 -----------------------------

		getBidList: function($scope, params, bidResource, $rootScope, dateHandling) {

			// ------------------ date 처리 ------------------
			// radio 박스 체크 처리에 필요한 객체
			// value = 1,3,6개월 전에 대한 값-1,3,6
			$scope.dateRadio = {
				"value": 0,
				"isChecked": [false, false, false],
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

				$scope.bidStartDate = dateHandling.prevMonth(num);
				$scope.bidEndDate = new Date();
			}

			$scope.dateChange = function() {
				for (let i = 0; i < $scope.dateRadio.isChecked.length; i++) {
					$scope.dateRadio.isChecked[i] = false;
				}
			}

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
					}
					$scope.items = res.data.items;

					for (let i = 0; i < $scope.items.length; i++) {
						$scope.items[i].bidInfo.isContracted = true;
						$scope.items[i].bidInfo.bidStartDate = dateHandling.longToDate($scope.items[i].bidInfo.bidStartDate);
						$scope.items[i].bidInfo.bidEndDate = dateHandling.longToDate($scope.items[i].bidInfo.bidEndDate);
						$scope.items[i].detailStatus = false;
						$scope.items[i].bidInfo.contractDate = //
							$scope.items[i].bidInfo.contractDate ? dateHandling.longToDate($scope.items[i].bidInfo.contractDate) : null;

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
				
				$scope.bidFiles = [];
				$scope.sampleFiles = [];

				bidResource.getBidDetail(
					{ "param1": id },
					null,
					function(res) {
						$scope.bid = res.data;

						let j = 0;
						let k = 0;
						for (let i = 0; i < $scope.bid.fileList.length; i++) {
							if ($scope.bid.fileList[i].fileType == 0) {
								$scope.bidFiles[j++] = $scope.bid.fileList[i];
							}
							else {
								$scope.sampleFiles[k++] = $scope.bid.fileList[i];
							}
						}
						
						$scope.bidFileLength = $scope.bidFiles.length;
						$scope.sampleFileLength = $scope.sampleFiles.length;

						$scope.bid.bidInfo.bidStartDate = dateHandling.longToDate(res.data.bidInfo.bidStartDate);
						$scope.bid.bidInfo.bidEndDate = dateHandling.longToDate(res.data.bidInfo.bidEndDate);
						$scope.bid.bidInfo.workStartDate = dateHandling.longToDate(res.data.bidInfo.workStartDate);
						$scope.bid.bidInfo.workEndDate = dateHandling.longToDate(res.data.bidInfo.workEndDate);
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