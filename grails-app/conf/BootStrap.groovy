import com.theconnman.relay.domains.Role
import com.theconnman.relay.domains.User
import com.theconnman.relay.domains.UserRole

class BootStrap {

	def springSecurityService

	def createUser(user, role) {
		def theUser = new User(username:user)
		theUser.save(flush:true)
		UserRole.create theUser, role, true
	}

	def init = { servletContext ->
		log.info 'Boostrapping'
		TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"))
		try {
			if (UserRole.list().size() == 0) {
				def adminRole = new Role(authority:'ROLE_ADMIN').save(flush:true)
				def userRole = new Role(authority:'ROLE_USER').save(flush:true)
				
				createUser('TheConnMan', adminRole)
				createUser('madjenjen', adminRole)
			}
		} catch (Exception e) {
			log.error("Exception during bootstrap init", e)
		}
	}
}
