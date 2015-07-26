package com.theconnman.relay.domains

class Message {

	String clientId
	String payload
	Date dateCreated

	static constraints = {
		clientId()
		payload maxSize: 5000
		dateCreated()
	}
}
