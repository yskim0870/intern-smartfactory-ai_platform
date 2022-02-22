platform.factory("Factory", function($resource) {

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

				$scope.startDate = function(num) {
					let month = new Date();
					month.setMonth(month.getMonth() - num);
					return month;
				}

				$scope.endDate = new Date();
			},

			// 날짜 변경
			dateChange: function($scope) {
				for (let i = 0; i < $scope.dateRadio.isChecked.length; i++) {
					$scope.dateRadio.isChecked[i] = false;
				}
			},

			// 1,3,6 개월 라디오 박스에 대한 처리
			// month: 1, 3, 6 중의 하나. 현재 날짜에서 month를 뺀 날짜를 구하기 위한 메소드
			prevMonth: function(month) {
				let date = new Date();
				let nowMonth = date.getMonth();

				date.setMonth(nowMonth - month);

				return date;
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
		
		// --------------------------- Common Method ---------------------------
		// 박경훈 코드
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

				// userType에 따라 url parmeter가 달라지기 위한 설정
				let getUserType = function() {
					// 제조사 : 사업자 번호
					if (params.userType == 1) {
						return item.companyInfo.businessNumber;
					}
					// 전문업체 : 회사 이름
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
							item.manu = res.data;
						}
						else if (params.userType == 2) {
							// 전문업체 정보와 자격증 정보는 한번에 가져오기, 입찰정보는 따로
							item.expert = res.data;
							// 입찰목록 조회
							getBidList(item);
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
					"contractorID": item.expertInfo.userID,
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
						item.bidLength = res.data.items.length;
						item.bids = res.data.items;

						for (let i = 0; i < item.bids.length; i++) {
							item.bids[i].bidInfo.contractDate = item.bids[i].bidInfo.contractDate ? //
								dateHandling.longToDate(item.bids[i].bidInfo.contractDate) : null;
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

			// 라디오 박스가 체크되어 있지만 input 박스로 직접 날짜를 입력할 경우
			// 라디오 박스 체크 해제
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
					// 대시보드에서 입찰공고 목록을 조회하기 위해 별도로 사용하는 변수 $scope.dash
					if ($scope.dash != 1) {
						$scope.pagination.totalCount = res.data.totalCount;
					}
					$scope.items = res.data.items;

					$scope.items.forEach(function(item) {
						// isContracted = 계약 등록/완료 버튼을 위한 ng-if문의 변수
						item.bidInfo.isContracted = true;
						item.bidInfo.bidStartDate = dateHandling.longToDate(item.bidInfo.bidStartDate);
						item.bidInfo.bidEndDate = dateHandling.longToDate(item.bidInfo.bidEndDate);
						item.detailStatus = false;

						// 계약이 완료되면 계약등록 버튼을 사라지게 하기 위한 설정 - isContracted
						if (item.bidInfo.status == 2) {
							item.bidInfo.contractDate = dateHandling.longToDate(item.bidInfo.contractDate);
							item.bidInfo.isContracted = false;
						}
						else {
							item.bidInfo.isContracted = true;
							item.bidInfo.contractDate = null; // 0으로 표시되는 것을 막기 위한 설정
						}
					});
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
						item.detail = res.data;

						// 입찰공고문, 샘플데이터를 나누기 위한 설정
						item.detail.fileList.forEach(function(file) {
							if (file.fileType == 0) {
								$scope.bidFiles.push(file);
							}
							else if (file.fileType == 1) {
								$scope.sampleFiles.push(file);
							}
						});

						item.detail.bidFileLength = $scope.bidFiles.length;
						item.detail.sampleFileLength = $scope.sampleFiles.length;
						item.detail.bidInfo.bidStartDate = dateHandling.longToDate(res.data.bidInfo.bidStartDate);
						item.detail.bidInfo.bidEndDate = dateHandling.longToDate(res.data.bidInfo.bidEndDate);
						item.detail.bidInfo.workStartDate = dateHandling.longToDate(res.data.bidInfo.workStartDate);
						item.detail.bidInfo.workEndDate = dateHandling.longToDate(res.data.bidInfo.workEndDate);
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