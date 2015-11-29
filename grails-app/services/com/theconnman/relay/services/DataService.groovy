package com.theconnman.relay.services

import grails.transaction.Transactional

import com.theconnman.relay.domains.DataValue

@Transactional
class DataService {

	DataValue storeValue(String clientId, double value) {
		return new DataValue(clientId: clientId, value: value).save()
	}
}
