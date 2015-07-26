package com.theconnman.relay.domains

class Message {

	String clientId
	String message

	static constraints = {
		clientId()
		message maxSize: 5000
	}
}
