package com.theconnman.relay.services

import com.theconnman.relay.domains.Message
import com.theconnman.relay.exceptions.MessageRelayException
import grails.converters.JSON
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

	void putMessage(String clientId, String payload) {
		if (!payload) {
			throw new MessageRelayException('No payload')
		}
		Map payloadMap = JSON.parse(payload)
		if (payloadMap.keySet().size() == 0) {
			throw new MessageRelayException('Payload is not valid JSON')
		}
		Message message = new Message(clientId: clientId, payload: payload)
		message.save()
		if (message.hasErrors()) {
			throw new MessageRelayException('Message save error: ' + message.errors)
		}
	}
}
