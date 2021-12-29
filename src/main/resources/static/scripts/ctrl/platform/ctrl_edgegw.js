platform.controller('EdgeController', function($scope, $resource) {

	let res = $resource(
		"edge-gws/:val",
		null,
		{
			createEdge: {
				method: 'POST',
			},

			getEdges: {
				method: 'GET',
				params: {
					managerId: "",
					startDate: "",
					endDate: ""
				}
			},

			getEdge: {
				method: 'GET',
				params: { val: "" }
			},

			updateEdge: {
				method: 'PATCH',
				params: { val: "" }
			},

			deleteEdge: {
				method: 'DELETE',
				params: { val: "" }
			}
		}
	);

	$scope.createEdge = function(id, managerId, startDate, endDate) {
		res.createEdge(
			{}
			, {
				id: id,
				managerId: managerId,
				startDate: startDate,
				endDate: endDate
			}
			, function() {
				$scope.getEdges();
			}
			, function() {
				alert("create fail");
			}
		)
	};

	$scope.getEdges = function(managerId, startDate, endDate) {
		res.getEdges(
			{
				managerId: managerId,
				startDate: startDate,
				endDate: endDate
			}
			, {}
			, function(res) {
				$scope.edgeGWs = res;
			}
			, function() {

			}
		);
	}

	$scope.getEdge = function(id) {
		res.getEdge(
			{
				val: id,
			}
		)
	};

	$scope.updateEdge = function(id) {
		res.updateEdge(
			{
				val: id
			}
			, {
			}
			, function() {
				$scope.getEdges();
			}
			, function() {
				alert("update fail");
			}
		)
	};

	$scope.deleteEdge = function(id) {
		res.deleteEdge(
			{ val: id }
			, null
			, function() {
				$scope.getEdges();
			}
			, function() {
				alert("delete fail");
			}
		)
	};
	
	$scope.opts = {
		dateFormat: 'dd/mm/yy',
		changeMonth: true,
		changeYear: true
	};
})