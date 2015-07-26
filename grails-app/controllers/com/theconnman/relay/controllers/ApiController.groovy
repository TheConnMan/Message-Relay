package com.theconnman.relay.controllers

import com.theconnman.relay.domains.Message
import com.theconnman.relay.exceptions.MessageRelayException
import com.theconnman.relay.services.MessageService
import grails.converters.JSON

class ApiController {

	def grailsApplication
	MessageService messageService

	def v1(String function, String key, String clientId) {
		try {
			String apiKey = grailsApplication.config.relay.api.key
			Map result = [success: true]
			if (apiKey != key) {
				throw new MessageRelayException('Invalid API key')
			}
			if (!clientId) {
				throw new MessageRelayException('No client ID')
			}
			if (function == 'get') {
				String payload = messageService.getMessage(clientId)
				if (payload) {
					result.message = JSON.parse(payload)
				}
			}
			render(result as JSON)
		} catch (MessageRelayException e) {
			log.warn e.getMessage()
			render([success: false] as JSON)
		} catch (e) {
			log.error e.getMessage()
			render([success: false] as JSON)
		}
	}
}
