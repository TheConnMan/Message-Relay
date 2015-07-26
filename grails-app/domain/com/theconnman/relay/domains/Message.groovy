package com.theconnman.relay.domains

class Message {

	String clientId
	String message
	Date dateCreated

	static constraints = {
		clientId()
		message maxSize: 5000
		dateCreated()
	}
}
