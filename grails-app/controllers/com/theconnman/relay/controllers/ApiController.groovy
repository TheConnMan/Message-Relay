package com.theconnman.relay.controllers

import grails.converters.JSON
import grails.plugin.slack.SlackService

import com.theconnman.relay.exceptions.MessageRelayException
import com.theconnman.relay.services.MessageService

class ApiController {

	def grailsApplication
	MessageService messageService
	SlackService slackService

	def v1(String function, String key, String clientId, String payload) {
		try {
			String apiKey = grailsApplication.config.relay.api.key
			Map result = [ok: true]
			if (apiKey != key) {
				throw new MessageRelayException('Invalid API key')
			}
			if (function == 'get') {
				String messagePayload = messageService.getMessage(clientId)
				if (messagePayload) {
					result.success = true
					result.message = JSON.parse(messagePayload)
				} else {
					result.success = false
				}
			}
			if (function == 'put') {
				messageService.putMessage(clientId, payload)
				result.success = true
			}
			if (function == 'slack') {
				slackService.send {
					text params.message
					channel params.channel ?: '#testing'
				}
				result.success = true
			}
			render(result as JSON)
		} catch (MessageRelayException e) {
			log.warn e.getMessage()
			render([ok: false, error: e.getMessage()] as JSON)
		} catch (e) {
			log.error e.getMessage()
			render([ok: false, error: e.getMessage()] as JSON)
		}
	}
}
