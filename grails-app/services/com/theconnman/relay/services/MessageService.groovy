package com.theconnman.relay.services

import com.theconnman.relay.domains.Message
import grails.transaction.Transactional

@Transactional
class MessageService {

	String getMessage(String clientId) {
		Collection<Message> messages = Message.findAllByClientId(clientId, [sort: 'dateCreated', order: 'asc'])
		if (messages.size() > 0) {
			Message message = messages.first()
			String payload = message.payload
			message.delete()
			return payload
		}
		return null
	}
}
