package com.theconnman.relay.controllers

import com.theconnman.relay.domains.Message
import com.theconnman.relay.exceptions.MessageRelayException
import grails.converters.JSON

class ApiController {

	def grailsApplication

	def v1(String function, String key, String clientId) {
		try {
			String apiKey = grailsApplication.config.relay.api.key
			if (apiKey != key) {
				throw new MessageRelayException('Invalid API key')
			}
			if (!clientId) {
				throw new MessageRelayException('No client ID')
			}
			render([success: true] as JSON)
		} catch (MessageRelayException e) {
			log.warn e.getMessage()
			render([success: false] as JSON)
		} catch (e) {
			log.error e.getMessage()
			render([success: false] as JSON)
		}
	}
}
