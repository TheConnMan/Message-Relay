package com.theconnman.relay.exceptions

class MessageRelayException extends RuntimeException {

	String message

	MessageRelayException(String message) {
		this.message = message
	}

	String getMessage() {
		return this.message
	}
}
