platform.controller("DashboardCtrl", function($scope, $rootScope, $uibModal, Factory) {
	$scope.userGrade = $rootScope.authentication.grade;
	// 대시보드에서 입찰공고 목록을 조회하기 위해 별도로 사용하는 변수 - factory에서 구현
	$scope.dash = 1;

	$scope.currentTime = new Date();

	// factory 선언
	let bidResource = Factory.bidResource;
	let dashboardResource = Factory.dashboardResource;
	let dateHandling = Factory.dateHandling;

	$scope.dashboard = {
		"bidCount": null,
		"manufacturerCount": null,
		"edgeGWCount": {
			"totalCount": null,
			"normalCount": null,
			"abnormalCount": null
		},
		"expertCount": null
	}

	// 대시보드의 상단 카운트 조회
	$scope.selectCount = function() {
		dashboardResource.getDashBoardCount(
			null,
			null,
			function(res) {
				$scope.dashboard.manufacturerCount = res.data.manufacturerCount;
				$scope.dashboard.bidCount = res.data.bidCount;
				$scope.dashboard.edgeGWCount = res.data.edgeGWCount;
				$scope.dashboard.expertCount = res.data.expertCount;
			},
			function(res) {
				alert(res);
			}
		);
	}
	
	// 목록 출력을 위한 조건을 담은 params	
	let params = {
		"pageNum": 0,
		"pageItemPerPage": 5,
		"status": null,
		"orderby": "bid_info.bid_start_date",
		"desc": true
	}

	let getList = function() {
		// 대시보드 입찰공고 목록 조회하는 부분
		Factory.getBidList($scope, params, bidResource, $rootScope, dateHandling);
	}

	getList();

	// 입찰공고, 입찰결과를 클릭했을 경우 목록 출력
	$scope.changeDashList = function(title) {
		if (title == '공고') {
			params.status = null;
		}
		else if (title == '결과') {
			params.status = 2;
		}
		
		getList();
	}

	// 상세보기 호출
	$scope.moveDetailPage = function(id) {
		let modalInstance = $uibModal.open({
			templateUrl: "/static/templates/platform/dashboard/bid_detail_modal.html",
			controller: "DashboardBidDetailCtrl",
			size: "md",
			resolve: {
				id: function() {
					return id;
				}
			}
		});

		// Modal 이 닫혔을때(ok, cancel) 받는 이벤트 부분
		modalInstance.result.then(
			function(bid) { // ok
				console.log("modal result : " + bid);
			},
			function(res) { // cancel
				console.log('modal dismissed at: ' + new Date() + '\n' + res);
			}
		);
	}

	let companyName = function() {
		if ($rootScope.authentication == 1) {
			// --- 회사명(제조사만 따로 조회하기 위한 제조사명) 조회 --- //
			let name = "";
			bidResource.getCompanyInfo(
				{
					"param1": "company",
					"param2": id // 제조사 아이디
				},
				null,
				function(res) {
					name = res.data.name;
				},
				function(res) {
					alert(res);
				}
			);
			return name;
		}
		else {
			return null;
		}
	}

	// 대시보드 하단 EdgeGW 상태 조회
	$scope.selectEdgeCount = function() {
		dashboardResource.getEdgeCount(
			{
				param1: 'edge-gw',
				param2: companyName()
			},
			null,
			function(res) {
				$scope.dashboard.edgeGWCount.totalCount = res.data.totalCount;
				$scope.dashboard.edgeGWCount.normalCount = res.data.normalCount;
				$scope.dashboard.edgeGWCount.abnormalCount = res.data.abnormalCount;
				$scope.dashLoad();
			},
			function(res) {
				alert(res);
			}
		);
	}

	/**        pie chart  -s-        */
	$scope.dashLoad = function() {
		//  window.onload = function(){
		let ctx = document.getElementById('pieChart');

		let normal = {
			'label': '정상',
			'value': $scope.dashboard.edgeGWCount.normalCount
		}
		let nonNormal = {
			'label': '비정상',
			'value': $scope.dashboard.edgeGWCount.abnormalCount
		}

		let chartData = [normal.value, nonNormal.value];
		let chartLabels = [normal.label, nonNormal.label];

		let pieChart = new Chart(ctx, {
			type: 'pie',
			data: {
				labels: chartLabels,
				datasets: [{
					data: chartData,
					backgroundColor: [
						'rgb(0, 191, 255)',
						'rgb(255, 165, 0)'
					],
					borderColor: [
						'rgb(255, 255, 255)',
						'rgb(255, 255, 255)'
					],
					borderWidth: 5
				}]
			},
			options: {
				responsive: false,
				legend: {
					display: false
				},
				legendCallback: function(chart) {
					let tag = [];
					tag.push('<ul class="0-legend">');
					let ds = chart.data.datasets[0];
					// for문 사용하지 않고 배열 합을 구하기 위해 reduce() 사용(callback)
					let sum = ds.data.reduce(function add(a, b) {
						// a -> 반환값, b -> 현재(다음) 인덱스
						return a + b;
					});
					tag.push('<li>');
					tag.push('<span style="background-color:black"></span>전체 : ' + sum + '개');
					tag.push('</li>');
					for (let i = 0; i < ds.data.length; i++) {
						tag.push('<li>');
						let percent = Math.round(100 * ds.data[i] / sum, 0);
						tag.push('<span style="background-color:' + ds.backgroundColor[i] + '">' //
							+ '</span>' + chart.data.labels[i] + ' : ' + ds.data[i] + '개 (' + percent + '%)');
						tag.push('</li>');
					}
					tag.push('</ul>');
					return tag.join("");
				},
				plugins: {
					labels: {
						render: 'label',
						fontColor: 'white',
						fontSize: 14,
						fontStyle: 'bold'
					}
				}
			}
		});

		let myLegendContainer = document.getElementById("legend");

		myLegendContainer.innerHTML = pieChart.generateLegend();
	}
	/**        pie chart  -e-        */
});