package com.theconnman.relay.taglibs

class MessageRelayTagLib {

	def springSecurityService

	static namespace = 'relay'

	def avatar = { attrs ->
		out << '<img class="' + attrs['class'] + '" style="' + attrs['style'] + '" src="' + springSecurityService.currentUser.avatarUrl + '" />'
	}
}