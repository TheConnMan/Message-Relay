package com.theconnman.relay.domains

class DataValue {

	String clientId
	double value
	Date dateCreated

	static constraints = {
		clientId()
		value()
		dateCreated()
	}
}
