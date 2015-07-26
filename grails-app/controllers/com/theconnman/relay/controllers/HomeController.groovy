package com.theconnman.relay.controllers

import com.theconnman.relay.domains.User
import grails.plugin.springsecurity.annotation.Secured

class HomeController {

	def index() { }
	
	def health() {
		render(status: 200)
	}
}

