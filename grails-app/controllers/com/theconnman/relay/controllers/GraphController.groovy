package com.theconnman.relay.controllers

import com.theconnman.relay.domains.DataValue

class GraphController {

	def index() {
		Collection<String> clientIds = DataValue.createCriteria().list {
			projections {
				distinct 'clientId'
			}
		}.sort()
		[clientIds: clientIds]
	}
}
