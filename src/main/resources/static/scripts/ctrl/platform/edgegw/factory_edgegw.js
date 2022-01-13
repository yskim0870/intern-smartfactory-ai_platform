platform.factory('EdgeFactory', function($resource) {

	return {
		resource: $resource(
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
		)
	}
})