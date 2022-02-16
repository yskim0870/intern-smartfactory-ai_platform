platform.factory("Factory", function($resource) {

	// 최근 개월 수
	let recentMonth = function(num) {

		let month = new Date();
		month.setMonth(month.getMonth() - num);

		return month
	}

	return {
		// --------------------------- Date Method ---------------------------
		dateHandling: {
			dateRadioClick: function($scope, num) {
				if (num == 1) {
					$scope.dateRadio.isChecked[0] = true;
				} else if (num == 3) {
					$scope.dateRadio.isChecked[1] = true;
				} else if (num == 6) {
					$scope.dateRadio.isChecked[2] = true;
				}

				$scope.startDate = recentMonth(num);

				$scope.endDate = new Date();
			},

			// 날짜 변경
			dateChange: function($scope) {
				for (let i = 0; i < $scope.dateRadio.isChecked.length; i++) {
					$scope.dateRadio.isChecked[i] = false;
				}
			},

			dateToLong: function(date) {
				if (date != null) {
					console.log(new Date(date).valueOf());
					return new Date(date).valueOf();
				}
			},
		},
		// --------------------------- Common Method ---------------------------
		common: {
			// 상세 보기 접기 / 펼치기
			lookDetail: function(info) {
				info.show = !info.show;
			},

			// 데이터 정렬
			sortData: function($scope, order) {
				$scope.reverseSort = !$scope.reverseSort;
				$scope.order = order;
				$scope.desc = $scope.reverseSort
			}
		},


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
					"param1": null,
					"param2": null
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

		companyResource: $resource(
			'/users/:param1/:param2', // param1 : {grade}, param2: {id}
			null,
			{
				// 제조사 목록 조회
				"getCompanyList": {
					"method": "GET",
					"param1": null
				},

				// 제조사 상세보기 조회	
				"getCompanyDetail": {
					"method": "GET",
					"param1": null,
					"param2": null
				},

				// 업태 조회
				"getCondition": {
					"method": "GET",
					"param1": null
				},

				// 업종 조회
				"getIndustryType": {
					"method": "GET",
					"param1": null
				},
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
				return new Date(date).valueOf();
			}
		},


		// ---------------------- 업체(제조사, 전문업체) 조회 -----------------------------

		getCompanyList: function($scope, params, companyResource, $rootScope, dateHandling, bidResource) {

			$scope.items = [];

			// 제조사만 전체 조회 user_type를 이용하여 출력
			companyResource.getCompanyList(
				{
					"param1": params.userType,
					"name": params.name ? params : null,
					"condition": params.condition ? params.condition : null,
					"industryType": params.industryType ? params.industryType : null,
					"orderby": params.order ? params.order : null,
					"desc": params.desc ? params.desc : null,
					"pageNum": params.pageNum,
					"pageItemPerPage": params.pageItemPerPage
				},
				null,
				function(res) {
					$scope.pagination.totalCount = res.data.totalCount;
					$scope.items = res.data.items;
				},
				function(res) {
					alert(res);
				}
			);

			// ------------------ detail ------------------

			let showDetail = function(item) {

				let getUserType = function() {
					if (params.userType == 1) {
						return item.companyInfo.businessNumber;
					}
					else if (params.userType == 2) {
						return item.companyInfo.name;
					}
				}

				companyResource.getCompanyDetail(
					{
						"param1": params.userType,
						"param2": getUserType()
					},
					null,
					function(res) {
						if (params.userType == 1) {
							$scope.manu = res.data;
						}
						else if (params.userType == 2) {
							// 전문업체 정보와 자격증 정보는 한번에 가져오기, 입찰정보는 따로
							$scope.expert = res.data;
						}

						item.detailStatus = !item.detailStatus;
					},
					function(res) {
						alert(res);
					}
				);
			}

			// 입찰정보는 따로 resource로 가져오기
			let getBidList = function(item) {
				let params = {
					"contractorID": item.userInfo.name,
					"orderby": null,
					"desc": false,
					"status": 1,
					"pageNum": 10,
					"pageItemPerPage": 10
				}

				bidResource.getBidList(
					{
						"param1": "expert",
						"param2": params.contractorID,
						"orderby": params.orderby,
						"desc": params.desc,
						"status": params.status,
						"pageNum": params.pageNum,
						"pageItemPerPage": params.pageItemPerPage
					},
					null,
					function(res) {
						// 전체 데이터 개수
						$scope.bidLength = res.data.items.length;
						$scope.bids = res.data.items;

						for (let i = 0; i < $scope.bids.length; i++) {
							$scope.bids[i].bidInfo.contractDate = $scope.bids[i].bidInfo.contractDate ? //
								dateHandling.longToDate($scope.bids[i].bidInfo.contractDate) : null;
						}
					},
					function(res) {
						alert(res.data);
					}
				);
			}

			// 공고 목록 클릭시
			$scope.selectDetail = function(item) {
				// 상세보기 조회
				showDetail(item);
				if (params.userType == 2) {
					// 입찰목록 조회
					getBidList(item, params);
				}
			}
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

			let getParam1 = function() {
				// 입찰관리 페이지 이동
				if (params.userGrade == 1) {
					return "mgmt";
				}
				// 일반 조회
				else {
					return null;
				}
			}

			let getParam2 = function() {
				// 입찰관리 페이지(해당 아이디의 제조사만 출력)
				if (params.userID != null) {
					return params.userID;
				}
				// 일반 조회
				else {
					return null;
				}
			}

			bidResource.getBidList(
				{
					"param1": getParam1(),
					"param2": getParam2(),
					"id": params.id ? params.id : null,
					"bidStartDate": params.bidStartDate ? params.bidStartDate : null,
					"bidEndDate": params.bidEndDate ? params.bidStartDate : null,
					"bidName": params.bidName ? params.bidName : null,
					"manufacturerName": params.manufacturerName ? params.manufacturerName : null,
					"status": params.status ? params.status : null,
					"orderby": params.orderby ? params.orderby : null,
					"desc": params.desc,
					"pageNum": params.pageNum,
					"pageItemPerPage": params.pageItemPerPage
				},
				null,
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
						$scope.items[i].bidInfo.contractDate = $scope.items[i].bidInfo.contractDate ? //
							dateHandling.longToDate($scope.items[i].bidInfo.contractDate) : null;

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

						$scope.bid.fileList.forEach(function(file) {
							if (file.fileType == 0) {
								$scope.bidFiles.push(file);
							}
							else {
								$scope.sampleFiles.push(file);
							}
						});

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