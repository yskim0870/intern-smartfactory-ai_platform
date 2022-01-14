// 보류

platform.factory('DateFactory', function() {

	return {
		dateFunction: function($scope) {
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

				$scope.startDate = recentMonth(num);

				$scope.endDate = new Date();
			};

			// 데이터 정렬
			$scope.reverseSort = false;
			$scope.sortData = function(order) {
				$scope.reverseSort = !$scope.reverseSort;
				$scope.order = order;
				$scope.desc = $scope.reverseSort
				$scope.getEdges();
			}

			// 검색 날짜 선택
			let recentMonth = function(num) {
				let month = new Date();
				month.setMonth(month.getMonth() - num);

				return month;
			};

			// 검색 날짜 선택
			$scope.dateChange = function() {
				for (let check = 0; check < $scope.dateRadio.isChecked.length; check++) {
					$scope.dateRadio.isChecked[check] = false;
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

			//	pagination
			$scope.pageNum = 1;
			$scope.maxSize = 5;
			$scope.bigTotalItems = 175;
			$scope.bigCurrentPage = 1;
			$scope.setPage = function(pageNum) {
				$scope.pageNum = pageNum;
			};

		}
	}
})