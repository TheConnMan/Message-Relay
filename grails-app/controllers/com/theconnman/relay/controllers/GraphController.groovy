package com.theconnman.relay.controllers

import grails.converters.JSON

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

	def data() {
		render(DataValue.findAllByClientId(params.clientId) as JSON)
	}
}
