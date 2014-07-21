import com.sra.DBBackupService
import com.sra.Role
import com.sra.User
import com.sra.UserRole
import org.springframework.security.oauth2.provider.BaseClientDetails

class BootStrap {

	def springSecurityService
	def clientDetailsService
	def searchableService

	def createUser(user,pass,role) {
		def theUser = new User(username:user,password:pass)
		theUser.save(flush:true)
		UserRole.create theUser, role, true
		clientDetailsService.clientDetailsStore << [(user): new BaseClientDetails()]
	}

	def init = { servletContext ->
		log.info 'Boostrapping'
		searchableService.stopMirroring()
		TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"))
		try {
			if (UserRole.list().size() == 0) { //only load on empty DB
				def adminRole = new Role(authority:'ROLE_ADMIN').save(flush:true)
				def userRole = new Role(authority:'ROLE_USER').save(flush:true)
	
				createUser('admin','stbadmin2014',adminRole)
				createUser('user','stbuser2014',userRole)
			}
		} catch (Exception e) {
			log.error(e.toString())
			e.printStackTrace()
		}
		def backup = new DBBackupService()
		backup.registerListener()
		searchableService.startMirroring()
	}
	def destroy = {
		
	}
}
