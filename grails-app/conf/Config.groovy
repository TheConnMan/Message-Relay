def loc = ['../UserConfig.groovy', 'webapps/ROOT/Jenkins.groovy'].grep { new File(it).exists() }.first();
def localConfig = new ConfigSlurper(grailsSettings.grailsEnv).parse(new File(loc).toURI().toURL())

grails.project.groupId = appName
grails.mime.file.extensions = true
grails.mime.use.accept.header = false
grails.mime.types = [
	all:			'*/*',
	atom:			'application/atom+xml',
	css:			'text/css',
	csv:			'text/csv',
	form:			'application/x-www-form-urlencoded',
	html:			['text/html','application/xhtml+xml'],
	js:				'text/javascript',
	json:			['application/json', 'text/json'],
	multipartForm:	'multipart/form-data',
	rss:			'application/rss+xml',
	text:			'text/plain',
	csv: 			'text/csv',
	pdf: 			'application/pdf',
	rtf: 			'application/rtf',
	excel: 			'application/vnd.ms-excel',
	ods: 			'application/vnd.oasis.opendocument.spreadsheet',
	hal:			['application/hal+json','application/hal+xml'],
	xml:			['text/xml', 'application/xml']
]

grails.views.default.codec = "none"
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
grails.views.gsp.sitemesh.preprocess = true
grails.scaffolding.templates.domainSuffix = 'Instance'

grails.json.legacy.builder = false
grails.enable.native2ascii = true
grails.spring.bean.packages = []
grails.web.disable.multipart=false

grails.exceptionresolver.params.exclude = ['password']

grails.hibernate.cache.queries = false

environments {
	development {
		grails.logging.jul.usebridge = true
		grails.serverURL = "http://192.168.0.15:8080"
		oauth.providers.github.key = localConfig.oauth.github.key
		oauth.providers.github.secret = localConfig.oauth.github.secret
	}
	devdeploy {
		grails.logging.jul.usebridge = false
		grails.serverURL = "http://relay.theconnman.com"
		oauth.providers.github.key = localConfig.oauth.github.key.devdeploy
		oauth.providers.github.secret = localConfig.oauth.github.secret.devdeploy
	}
	production {
		grails.logging.jul.usebridge = false
		grails.serverURL = "http://relay.theconnman.com"
		oauth.providers.github.key = localConfig.oauth.github.key.prod
		oauth.providers.github.secret = localConfig.oauth.github.secret.prod
	}
}

relay {
	api {
		key = localConfig.relay.api.key
	}
}

oauth {
	providers {
		github {
			api = com.theconnman.GitHubApi
			successUri = '/github'
			failureUri = '/error'
			callback = "${ grails.serverURL }/oauth/github/callback"
		}
	}
}

log4j = {
	appenders {
		console name: 'stdout', threshold: org.apache.log4j.Level.ERROR
		rollingFile name: 'info', file: 'logs/info.log', layout: pattern(conversionPattern: '[%p] %d{yyyy-MM-dd HH:mm:ss} %c{2} - %m%n'), threshold: org.apache.log4j.Level.INFO
		rollingFile name: 'warn', file: 'logs/warn.log', layout: pattern(conversionPattern: '[%p] %d{yyyy-MM-dd HH:mm:ss} %c{2} - %m%n'), threshold: org.apache.log4j.Level.WARN
		rollingFile name: 'activity', file: 'logs/activity.log', layout:pattern(conversionPattern: '%d{dd-MM-yyyy HH:mm:ss} - %m%n'), threshold: org.apache.log4j.Level.INFO
	}

	warn 'warn': [
		'grails.app.controllers.com.theconnman.relay',
		'grails.app.services.com.theconnman.relay',
		'grails.app.conf.com.theconnman.relay',
		'grails.app.domain.com.theconnman.relay'
	]

	info 'info': [
		'grails.app.controllers.com.theconnman.relay',
		'grails.app.services.com.theconnman.relay',
		'grails.app.conf.com.theconnman.relay',
		'grails.app.domain.com.theconnman.relay'
	]

	info	activity: ['grails.app.filters.com.theconnman.relay.filters.LoggingFilters']
	off 'org.grails.plugin.resource.ResourceMeta'
}

grails.resources.resourceLocatorEnabled = true

grails.app.context="/"

grails.plugin.springsecurity.providerNames = [
	'daoAuthenticationProvider',
	'anonymousAuthenticationProvider',
	'rememberMeAuthenticationProvider'
]

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.theconnman.relay.domains.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.theconnman.relay.domains.UserRole'
grails.plugin.springsecurity.authority.className = 'com.theconnman.relay.domains.Role'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/**':								['permitAll'],
	'/message/**':						['ROLE_ADMIN'],
	'/user/**':							['ROLE_ADMIN'],
	'/role/**':							['ROLE_ADMIN'],
	'/registrationCode/**':				['ROLE_ADMIN'],
	'/securityInfo/**':					['ROLE_ADMIN']
]
